package com.feign.provider.userService;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

/**
 * 自定义负载均衡策略配置
 */
public class UserServiceRule implements IRule {

    ILoadBalancer balancer = new BaseLoadBalancer();

    @Override
    public Server choose(Object key) {
        List<Server> allServers = balancer.getAllServers();
        Random random = new Random();
        final int number = random.nextInt(10);
        if (number < 7) {
            return findServer(allServers, 8501);
        }
        return findServer(allServers, 8502);
    }

    private Server findServer(List<Server> allServers, int port) {
        for (Server server : allServers) {
            if (server.getPort() == port) {
                return server;
            }
        }
        System.out.println("NULL port=" + port);
        return null;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.balancer = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.balancer;
    }
}
