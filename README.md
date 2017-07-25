# cropportal

A web application based off of [http://www.cropontology.org/api](http://www.cropontology.org/api)

## Quickstart Using Docker ##

For Windows and OSX get Docker from [https://www.docker.com](https://www.docker.com). On Linux, Docker can be installed through a shell script hosted at [https://get.docker.com](https://get.docker.com).

```shell
wget https://get.docker.com -O install.sh
sh install.sh
```
Then with Docker installed, you can build an image from the `Dockerfile` provided in the main directory of this project.

```shell
cd biolink-beacon
docker build -t ncats:biolink .
```

Within the Docker container, the Spring application is set to run at `localhost:8080`. You can expose and re-map ports when you run a Docker image with the `-p` flag.

```shell
docker run -p 8080:8080 ncats:biolink
```

Now open your browser to `localhost:8080` to see the application running.
