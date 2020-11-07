current_dir=$(shell pwd)

build-app:
	./gradlew clean build --warning-mode=all

run:
	./gradlew bootRun --warning-mode=all

docker-run:
	./gradlew build --warning-mode=all
	docker-compose down
	docker-compose up --build -d

test:
	./gradlew test --warning-mode=all


pre-commit: test build-app