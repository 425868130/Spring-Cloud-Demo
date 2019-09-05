# 说明

本目录为spring config配置文件目录,config-server配置git地址指向当前仓库，并配置子目录扫描 `search-paths: config-resources/**` 。  
本目录下分文件夹保存各个微服务项目的多环境配置文件，文件夹名称与微服务项目模块名称一一对应，配置文件名称格式为: {微服务应用名}-{环境名}.yml  
其中base包下保存基础公共配置文件，其他模块通过 `spring.cloud.config.name=${spring.application.name},base` 指定多名称的方式引入
