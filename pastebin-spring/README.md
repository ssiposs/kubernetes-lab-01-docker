# pastebin-spring (Lab 1 submission)

Spring Boot mini-pastebin API (same contract as [`examples/docs/API.md`](../examples/docs/API.md)).

**Twists:** package `com.ssiposs.pastebin`, deduplicated URLs (same URL returns the same code), extra metadata on `GET /`.

## Run locally

```bash
docker compose up --build -d
curl -s http://localhost:8080/health
curl -s -X POST http://localhost:8080/paste \
  -H 'Content-Type: application/json' \
  -d '{"url":"https://www.docker.com/"}'
docker compose down
```

Published image: `docker.io/ssiposs/kubernetes-lab-01:lab1`

## Push to Docker Hub (after local testing)

```bash
docker login
docker build -t ssiposs/kubernetes-lab-01:lab1 .
docker push ssiposs/kubernetes-lab-01:lab1
```

## Git (when you are satisfied)

Set your identity if needed, then commit and push to your fork—no co-author trailers.

```bash
git add pastebin-spring/ README.md
git commit -m "Add Spring Boot pastebin Lab 1 submission with Debian multistage Docker image"
git push origin master
```
