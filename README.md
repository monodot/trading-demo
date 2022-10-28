# trading-demo

This is a demo trading application. It's a simple Java/Spring Boot microservice, for buying and selling stocks.

(Note that it doesn't really buy or sell anything, it's just a simulation.)

<p style="text-align: center"><img src="michael.jpg" width="350"/></p>

The application is designed to show how to add observability to a Java app, so it's configured with a couple of extra bits to make it easier to observe:

- **Metrics.** Using the open source library [Micrometer](https://micrometer.io), the application exposes some metrics at a Prometheus-compatible endpoint (`/actuator/prometheus`).

- **Logs.** The application writes logs to the console for each trade, which can be scraped and sent to a log aggregator like Promtail/Loki.

## Running the application in Kubernetes

| ☑️  To run locally |
| :----- |
| If you just want to run locally, you can use the `docker-compose.yml` file in the root of the repository. This will start the application, Prometheus, and Grafana. |

To run in Kubernetes, you will need:

- A Kubernetes cluster

- Grafana Agent installed in your Kubernetes cluster, configured to push metrics to your Grafana Cloud account, **OR** Prometheus installed in your Kubernetes cluster, if you're not using Grafana Cloud.

### Configure scraping

First we need to configure Prometheus or Grafana Agent to collect metrics from the application. This can be done in lots of different ways, but we're using annotations.

We've added these annotations to our Kubernetes `Service`:

```
prometheus.io/scrape: "true"
prometheus.io/port: "8080"
prometheus.io/path: "/actuator/prometheus"
```

These Kubernetes annotations are a fairly common way to advertise to a capable scraper that this application exposes metrics, and we want them to be scraped.

To make sure that our microservice's metrics get picked up by Grafana Agent or Prometheus, ensure that your Agent/Prometheus has a job to scrape annotated Kubernetes `endpoints`. The configuration for the job should look something like this:

```
configs:
- name: your-config-name
    ...
    scrape_configs:
    ...
    - job_name: integrations/kubernetes/pod-app-metrics
        kubernetes_sd_configs:
        - role: endpoints
        relabel_configs:
        - source_labels: [__meta_kubernetes_service_annotation_prometheus_io_scrape]
            action: keep
            regex: true
        - source_labels: [__meta_kubernetes_service_annotation_prometheus_io_path]
            action: replace
            target_label: __metrics_path__
            regex: (.+)
        - source_labels: [__address__, __meta_kubernetes_pod_annotation_prometheus_io_port]
            action: replace
            regex: ([^:]+)(?::\d+)?;(\d+)
            replacement: $1:$2
            target_label: __address__
```

Restart your Agent or Prometheus after making any changes. Then, Grafana Agent (or Prometheus) will automatically scrape the metrics from any `Services` with these `prometheus.io/*` annotations.

## Running the application locally

### Prerequisites

You will need to have the following installed on your machine:

- Java (if running standalone)
- Docker (if running the complete application + log collector in a container)

### Running the microservice

To run the microservice on its own, you can run the following command:

```bash
cd trading-service
./mvnw spring-boot:run
```

<!-- Also provided is a docker-compose file, which runs the application and a Promtail instance to send to Loki. To run it, you can run the following command:

```bash
docker-compose up -d
``` -->

## Running the tests

There aren't many tests at the moment 😓, but to run them, you can run the following command:

```bash
cd trading-service
./mvnw test
```

### Load test

Running a load test with k6.

**First, install k6.**

Then:

```
cd load-testing
npm install
npm run pretest
npm run test
```

This will run a short load test against the application, and output the results to the console.

## Built with

- [Maven](https://maven.apache.org) - The build tool used by all the coolest Java developers (and some of the not-so-cool ones)
- [Spring Boot](https://spring.io/projects/spring-boot) - Hugely popular application framework for Java
- [Micrometer](https://micrometer.io) - Application metrics library for Java that's vendor-neutral (which means you can use it to share data with Prometheus, InfluxDB, Datadog, etc.)
- [Prometheus](https://prometheus.io) - Time-series database for gathering and storing metrics.
- [k6](https://k6.io) - Load testing tool that we're slightly misusing to simulate traffic to the application.

## Authors

- **Tom Donohue** - [monodot](https://github.com/monodot)

---

Pssst. Some of this text was written with GitHub Copilot. 🤖 (Bet you can't tell which bits!)
