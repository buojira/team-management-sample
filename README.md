# Question: Description how you approached the problem and the solution.
Once there was real data in the urls passed in the doc, the first thing to do was to analyze the data
to think in a proper way to integrate the current data with Role.
For a better search performance I created the class ScriptController in order to read all Data in the urls and
store it locally. This also answer the question "What happens if the data you are using gets deleted?".
Because it only takes de time to call the loading script to reset the app data.

Once the data was ready to be analyzed, it was necessary to create the classes RoleController and MembershipController
using some Restful principles to store and retrieve data according to necessity.

Talking about Role and Membership, only these two domains had Unit Tests written for them, because these are
the solution fo the problem. All others domains are in this project just as support for the solution, but in a
real life situation, they should not be in this microservice.

Package v1 was created in order to do not break compatibility. The rest of the packages were organized thinking in domains.

I chose to do not use tools from postgres in order to maintain the app agnostic, so the respository can be
changed with minimum impact in the rest of the application.

Minimum impact was also the reason of using ExampleMatcher instead of writing queries. It is also much faster to
make an MVP in this way. In a real life situation it would be necessary to create metrics in order to see if
the performance of ExampleMatcher searches is acceptable for its workload.

I also opted to throw the minimum amount of Exceptions and just inform (as a warning) when queries do not do
what they were expected to do (E.G.: Deleting a row that does not exist. The app just inform nothing was done)

# Information on how to run the code
In the root folder, just execute the following command:
```
mvn clean install && docker-compose up --build
```
Other useful commands:
- build with docker:
```
docker build -t sample/team-mgmnt-docker .
```
- running app with docker:
```
docker run --rm --name team-mgmnt -p 8090:8080 sample/team-mgmnt-docker
```

# Suggestion for improvement in the Team or User services:
- Pagination and Sorting;
- Searches for fields that are not key (E.G.: Name, Leader etc...)