#Simple Movie Recommendor App

## Starting the application
### building the application
```
cd movie-recommendor
mvn clean install
```
### run the application
```
cd target
java -jar movieRecommendor-0.0.1-SNAPSHOT.jar
```

## API
### Get recommended movie
```
API: GET api/user/:id/recommendedMovie
Sample response:
[
    {
        "name": "3"
    },
    {
        "name": "5"
    },
    {
        "name": "4"
    },
    {
        "name": "1"
    },
    {
        "name": "2"
    }
]
```

## Sample social network app
### Repository
```
git clone https://github.com/yinchuangsum/social-media
```