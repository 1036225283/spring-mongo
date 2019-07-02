package xws.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xws.client.UserClient;
import xws.entity.User;
import xws.repository.UserRepository;

import java.util.List;

/**
 * test
 * Created by xws on 5/20/17.
 */

@RestController
@RequestMapping("mongo")
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserRepository userRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public List<ServiceInstance> serviceInstancesByApplicationName() {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String test() {

        User user = userClient.get();
        user.setId(1);
        user.setMsg("this is test,哈哈");
        user.setAge(30);
        user.setSex(1);
        userRepository.save(user);

        return JSON.toJSONString(user);
    }


}
