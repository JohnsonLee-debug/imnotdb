# README

## Pre-requirement

- [`openJDK`](https://www.injdk.cn/) 11
- `curl`
- `python` 3.8
- `pip  ` 21.1.2

## Dependencies

- [`MySQL`](https://hub.docker.com/_/mysql) 8.0
- [`elasticsearch`](https://hub.docker.com/_/elasticsearch)7.13.1
- [`elasticsearch-analysis-ik`](https://github.com/medcl/elasticsearch-analysis-ik/releases/tag/v7.13.1) 7.13.1
- `elasticsearch-loader` 0.6.0

#### MYSQL

```sh
# Bash
docker run --name imnotdb_mysql\
		   -e MYSQL_ROOT_PASSWORD=password\
           -e MYSQL_DATABASE=imnotdb\
           -e MYSQL_USER=imnotdb_user\
           -e MYSQL_PASSWORD=password\
           -p '3306:3306'\
           -d mysql:latest
```

```powershell
# powershell
docker run --name imnotdb_mysql `
		   -e MYSQL_ROOT_PASSWORD=password `
           -e MYSQL_DATABASE=imnotdb `
           -e MYSQL_USER=imnotdb_user `
           -e MYSQL_PASSWORD=password `
           -p '3306:3306' `
           -d mysql:latest
```

#### Elasticsearch

```sh
# bash
docker run --name imnotdb_elasticsearch\
		   -p 9200:9200\
           -p 9300:9300\
           -e ES_JAVA_OPTS="-Xms2g -Xmx2g"\
           -e "discovery.type=single-node"\
           -d elasticsearch:7.13.1
```

```powershell
# powershell
docker run --name imnotdb_elasticsearch `
-p 9200:9200 `
-p 9300:9300 `
-e ES_JAVA_OPTS="-Xms2g -Xmx2g" `
-e "discovery.type=single-node" `
-d elasticsearch:7.13.1
```

#### elasticsearch-analysis-ik

```sh
docker exec -it imnotdb_elasticsearch /bin/bash -c '/usr/share/elasticsearch/bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.13.1/elasticsearch-analysis-ik-7.13.1.zip'
docker restart imnotdb_elasticsearch
```

#### elasticsearch-loader

```sh
pip install elasticsearch-loader
```

## 数据导入

### MySQL数据导入

使用任意数据库连接工具登录用户`imnotdb_user`, 密码`password`,连接数据库`imnotdb`。

#### 建表

```mysql
create table title_akas(
                           `tconst` varchar(20),
                           `ordering` integer,
                           `title` varchar(400),
                           `region` varchar(200),
                           `language` varchar(200),
                           `types` varchar(20),
                           `attributes` varchar(200),
                           `isOriginalTitle` boolean
);
CREATE TABLE title_basics(
                             `tconst` VARCHAR(20) NOT NULL ,
                             `titleType` VARCHAR(50),
                             `primaryTitle` VARCHAR(200),
                             `originalTitle` VARCHAR(200),
                             `isAdult` BOOLEAN,
                             `startYear` INTEGER,
                             `endYear` INTEGER,
                             `runtimeMinutes` INTEGER,
                             `genres` VARCHAR(200),
                             primary key (tconst)
);
CREATE TABLE title_crew(
                           `tconst` VARCHAR(20) NOT NULL,
                           `directors` VARCHAR (200),
                           `writers` VARCHAR(200),
                           primary key(tconst)
);

CREATE TABLE title_principals(
                                 `tconst` VARCHAR(20) NOT NULL ,
                                 `nconst` VARCHAR(20) NOT NULL ,
                                 `ordering` INTEGER,
                                 `category` VARCHAR (200),
                                 `job` VARCHAR (200),
                                 `characters` VARCHAR(200)
);
CREATE TABLE ratings(
                        `tconst` VARCHAR(20) NOT NULL ,
                        `averageRating` FLOAT,
                        `numVotes` BIGINT,
                        primary key (tconst)
);
create table name(
                     nconst varchar(20) not null ,
                     primaryName varchar(200),
                     birthYear integer,
                     deathYear integer,
                     primaryProfession varchar(200),
                     knownForTitles varchar (100),
                     primary key(nconst)
);
create table title_full (
                            `tconst` VARCHAR(20) NOT NULL ,
                            `titleType`VARCHAR(50),
                            `primaryTitle`VARCHAR(200),
                            `akaTitles` TEXT,
                            `isAdult` BOOLEAN,
                            `startYear` INTEGER,
                            `endYear` INTEGER,
                            `runtimeMinutes` INTEGER,
                            `genres` VARCHAR(200),
                            `actors` TEXT,
                            `directors` TEXT,
                            `writers` TEXT,
                            `averageRating` FLOAT,
                            primary key (tconst)
);
-- 索引建立需要在数据导入完成后进行
create index tconst_index on title_principals(tconst);
create index nconst_index on title_principals(nconst);
create index akasIndexByTconst on title_akas(tconst);
create fulltext index primaryNameFullIndex on name(primaryName);
```

#### 导入数据

使用任意数据库连接工具导入数据，需要配置的地方如下：

- value separator: Tab
- Row separator: Newline
- Null value text: \N
- Quotation:无
- First row is header
- Encoding: UTF-8

### ElasticSearch数据导入

进入title_full所在目录下，进入终端执行以下命令

```sh
# 需要有curl
curl -H "Content-Type:application/json"\
	 -X PUT\
     -d \
'{
  "mappings":{
    "properties":{
      "tconst":{
        "type": "keyword"
      },
      "titleType":{
        "type": "keyword"
      },
      "primaryTitle":{
        "type": "text"
      },
      "akaTitles":{
        "type": "text"
      },
      "isAdult":{
        "type": "integer"
      },
      "startYear":{
        "type": "integer"
      },
      "endYear":{
        "type": "integer",
        "null_value": -1
      },
      "runtimeMinutes":{
        "type": "integer"
      },
      "genres":{
        "type": "text"
      },
      "actors":{
        "type": "text"
      },
      "directors":{
        "type": "text"
      },
      "writers":{
        "type": "text"
      },
      "averageRating":{
        "type": "double",
        "null_value": -1.0
      }
    }
  }
}'\
	http://localhost:9200/title_full
```

```sh
python3 -m elasticsearch_loader --index title_full --timeout 10000 json $到title_full.json的路径
```

## 运行主程序

```sh
java -jar $name_of_jar_package
```

## 数据集地址

项目数据集:[Json格式的title_full未上传，需要的话用tsv转json即可](https://pan.baidu.com/s/1Hio9Yubex25E7lCE37IIHw)

提取码: uvxk

