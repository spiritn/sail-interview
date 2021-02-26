#使用教程

## 0x1 引入maven依赖
引入资源同步包maven依赖，推荐放在common模块中（当然，放在其他也没啥事）
```
        <dependency>
            <groupId>com.hikvision.ebg</groupId>
            <artifactId>res-sync</artifactId>
            <version>1.1-SNAPSHOT</version>
        </dependency>
```

## 0x2 配置文件
在properties文件中配置你要同步的表，配置格式如下：
```
#资源同步定时任务，默认2点，可以不写
resource.sync.cron=0 0 2 * * ?
#是否同步用户，不写默认false
resource.sync.user=true
#同步pdms数据模型，逗号分隔，例如 tb_model1,tb_model2,tb_model3
resource.sync.models=tb_camera,tb_encode_device,tb_person,tb_org
#设备类型是否过滤，设备型号逗号分隔，默认啥都没有
resource.sync.device.type=
#是否为集群模式，不写默认为false，在集群模式下，所有同步操作需要获取分布式锁，保证每次只有一个节点执行
resource.sync.cluster=false
#如果需要逻辑删除数据，使用如下注解指定逻辑删除的表
resource.sync.models.tombstone=tb_person,tb_org
#包扫描范围，用于扫描模型的entity类，默认为com.hikvision.ebg
resource.sync.scan.package=com.hikvision.ebg
```

可以放在application.properties中，只要spring可以加载到就行。
模块默认可以支持如下数据同步，首先将相应的表建立好，然后通过配置开启：

>* tb_camera
>* tb_encode_device
>* tb_org
>* tb_region
>* tb_monitoring_point
>* tb_monitoring_point_device
>* tb_person
>* tb_person_face

注意：同步用户使用```resource.sync.user=true```属性

同步其他数据使用```resource.sync.models=tb_xxx,tb_yyy```

如果组件为集群模式，则需要通过配置
```$xslt
resource.sync.cluster=true
```
来开启集群模式，在集群模式下，所有资源同步操作需要获取分布式锁后才可以执行，
从而避免定时任务和topic消息重复执行。

个别组件需要对同步的设备进行过滤，将你需要的设备型号写入以下属性，逗号隔开
```
resource.sync.device.type=型号1,型号2,型号3
```

至此，数据同步基本完成。

## 0x3 同步自定义模型

如果组件需要同步自定义模型，或者其他pdms中的数据，需要做如下工作
1. 在自己组件中建立对应的模型表，表名称、表结构要和pdms中一致，例如表名为```tb_my_table```；
2. 新版本的资源同步jar包会在项目启动时自动建立临时表，因此无需在去人为建立xxx_temp表；
3. 建立对应的pojo类，强烈建议使用mybatis-plus生成，在pojo类上加注解`@TableName("表名")`，在pojo主键属性上加注解`@TableId`;
4. 配置文件中添加你要同步的表```resource.sync.models=tb_my_table```

通过以上四步就可以同步自定义模型了。

为了满足部分同学对模型中部分字段不同步的需求，添加一个@SyncIgnore注解
，将该注解放在Entity类上，则不同步该模型；将注解放在属性上，则不同步该属性。

## 0x4 注意事项
* mq要配置好，之前测试发现脚手架的service.properties缺少mq的配置
```$xslt
service.mq.segmentId=xxxmq
```
导致项目启动报错

* mybatis配置问题：
经多个同事测试发现，目前脚手架的mybatis的dao层扫描和mapper.xml扫描配置是有问题的，如果启动项目报错原因是XXXDao未找到或者XXXMapper未找到，
请修改脚手架相关配置；


* 如果是集群模式，redis相关配置文件也要配置好；

* 支持重试机制，重试20次，每次间隔3分钟；

* 支持数据逻辑删除，需要通过配置```resource.sync.models.tombstone```属性实现，默认物理删除

* 数据同步后，会发送事件：

>1.在全量或增量同步后，会将新增、删除、修改的数据作为事件发送出去；
>
>2.监听同步事件时使用```EventListener```或```TransactionalEventListener(fallbackExecution = true)```
>这里的注解中fallbackExecution是指在没有事务的情况下也要监听
```aidl
 public void onDeltaSyncEvent(DeltaSyncEvent event) {
        String tableName = event.getTableName();
        List data = event.getData();
        SyncOperation operation = event.getOperation();

        log.info("- begin to publish event for table {}, operation is {}, data is:\n{}", tableName, operation, data);
        switch (tableName) {
            case TABLE_CAMERA:
                publisher.publishEvent(new CameraEvent(data, operation));
                break;
            case TABLE_DEVICE:
                publisher.publishEvent(new DeviceEvent(data, operation));
                break;
            case TABLE_REGION:
                publisher.publishEvent(new RegionEvent(data, operation));
                break;
            case TABLE_ORG:
                publisher.publishEvent(new OrganizationEvent(data, operation));
                break;
            case TABLE_PERSON:
                publisher.publishEvent(new PersonEvent(data, operation));
                break;
            case TABLE_PERSON_FACE:
                publisher.publishEvent(new PersonFaceEvent(data, operation));
                break;
            default:
                publisher.publishEvent(new ModelEvent(data, operation, tableName));
        }
    }
```

* 各个表全量同步完成后会发送一个事件：
```aidl
publisher.publishEvent(new SyncEvent(tableName, data));
```
要使用TransactionalEventListener进行监听，从而避免异常导致事务未执行完成的问题。

* 可以通过实现DeviceFilterService接口并注册为bean实现自定义设备过滤：
下面是一个demo：
```aidl
@Component
public class DeviceFilterServiceImpl implements DeviceFilterService {
    @Override
    public List<FiledOptionDto> getDeviceOptionList() {

        FiledOptionDto option = new FiledOptionDto();
        option.setFieldName("device_type");
        option.setType("in");
        option.setFieldValue("XXX-001,XXX-002,XXX-003");

        return Collections.singletonList(option);
    }
}
```



