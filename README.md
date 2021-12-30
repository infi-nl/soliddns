# SOLID DNS

Started as a simple cli application and accompanying api for moving Dns records between two DNS providers.
(InternedServices and Cloudflare). Someday the interactive orchestration tool for managing DNS records across several more.

My initial purpose for this project was having my own java learning grounds and providing a simple tool for my colleagues to move away from the InternedServices DNS provider.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them


- JavaSDK 1.8, download [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and follow instructions.
- [Gradle](https://gradle.org/install/)

### Installing

A step by step series of examples that tell you have to get a development env running

Clone repository into your folder of choice

```
git clone https://github.com/infi-nl/soliddns.git folder

```

Import project into IDE of choice
```
IntelliJ IDEA Community Edition has been my IDE of choice with the IdeaVim plugin
File -> Open... -> Browse to folder where you cloned the repository
```

Setup the right application properties for your dev environment

```
1. Duplicate application.properties.template in the Cli project
2. Rename to application.properties
3. Fill out the needed details for your own DNS provider accounts
```

Export InternedServices csv

```
1. Follow needed steps for this provider to obtain a csv
2. Place it within the resources folder or a path of your own choosing
```

Run CliCsvApplication
```
In IntelliJ Idea:
1. Right click CliCsvApplicationBootstrap
2. Choose Run/Debug CliCsvApplicationBootstrap
```

You should be seeing some simple println output in your IDE confirming succesfull or failed pushes of the imported DNS Records


## Built With

* [Spring Boot](https://projects.spring.io/spring-boot/) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Authors

* **Matthijs Liethof** - *Initial work* - [MatthijsL](https://github.com/matthijsl)

See also the list of [contributors](https://github.com/infi-nl/soliddns/contributors) who participated in this project.

## License

This project is licensed under the Mozilla Public License Version 2.0 - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* [@edeckers](https://github.com/edeckers) for the idea, mentoring and reviewing code
* [@infi-nl](https://github.com/infi-nl) allowing time to work on the project
* [@PurpleBooth](https://github.com/PurpleBooth) excellent source for well setup readme.

