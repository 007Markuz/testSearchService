current_dir=$(shell pwd)

build-app:
	./gradlew clean build --warning-mode=all

run:
	./gradlew bootRun --warning-mode=all

docker-run:
	./gradlew build --warning-mode=all
	docker-compose down
	docker-compose up --build -d

unit-test:
	./gradlew test --warning-mode=all

integration-test:
	./gradlew integrationTest --warning-mode=all

test: unit-test

pre-commit: test build-app