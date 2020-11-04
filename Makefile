current_dir=$(shell pwd)

build-app:
	./gradlew clean build --warning-mode=all

run:
	./gradlew bootRun --warning-mode=all

docker-run:
	./gradlew build
	docker-compose down
	docker-compose up --build -d

unit-test:
	./gradlew unitTest --warning-mode=all

integration-test:
	./gradlew integrationTest --warning-mode=all

test: unit-test integration-test

pre-commit: sonar test dependency-check