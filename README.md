# Kotlin Reference App
This repo contains various Kotlin implementations for REST APIs.


## Postgres
### Setting Up Dev Environment

## Cassandra
### Setting Up Dev Environment
Cassandra and Datastax Studio will be setup as docker containers.

**Pull Images**
```
docker pull datastax/dse-server:latest
docker pull datastax/dse-studio:latest
```

**Run Containers**
```
# Start Cassandra
# The -g flag starts a Node with Graph Model enabled
# The -s flag starts a Node with Search Engine enabled
# The -k flag starts a Node with Spark Analytics enabled
#
docker run -e DS_LICENSE=accept --memory 4g --name my-dse -d -p 9042:9042 datastax/dse-server -g -s -k

# Start DSE Studio 
docker run -e DS_LICENSE=accept --link my-dse -p 9091:9091 --memory 1g --name my-studio -d datastax/dse-studio

#The -link parameter provides a way to map a hostname to a container IP address. In this example, 
# we map the database container to Studio container by providing its name, ‘my-dse’. 
```

To use volume
```
docker run --name some-cassandra -v /my/own/datadir:/var/lib/cassandra -d cassandra:tag
```

To stop:
```aidl
docker stop my-studio
docker stop my-dse

```