# LangChain4j Neo4j Graph RAG - Movies Search Demo

[![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)]()
[![Maintainer](https://img.shields.io/static/v1?label=Yevhen%20Ruban&message=Maintainer&color=red)](mailto:yevhen.ruban@extrawest.com)
[![Ask Me Anything !](https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg)]()
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
![GitHub release](https://img.shields.io/badge/release-v1.0.0-blue)

An AI-powered movie database application built with Java 21, Spring Boot 3.3.3, and Neo4j. The app uses LangChain4j to enable natural language queries, providing personalized recommendations, interactive graph visualizations, and dynamic data exploration. Users can effortlessly explore movies, actors, genres, and directors. Featuring scalable architecture and robust security, the platform delivers a seamless, personalized, and engaging movie discovery experience.




https://github.com/user-attachments/assets/9e39ca34-e78b-47d4-8e69-f5951545eb83





1. Query example:
```bash
MATCH p=()-[:IN_GENRE]->() RETURN p LIMIT 25;
```
Output:
![visualisation (2)](https://github.com/user-attachments/assets/877fd8c3-f490-4398-aff7-f2639e0dbd64)

## Key Features
- **Natural Language Query Support**: Leverage LangChain4j to allow users to ask movie-related questions in plain language, translating them into Cypher queries for Neo4j.
- **AI-Powered Recommendations**: Provide personalized movie, actor, and genre recommendations based on user queries, search history, and preferences.
- **Interactive Graph Visualizations**: Showcase relationships between movies, actors, directors, and genres using Neo4j's graph data in an intuitive and interactive format.
- **LangChain4j-Powered Insights**: Leverage LangChain4j for natural language understanding and effective query handling, ensuring accurate and contextually relevant responses.
- **TogetherAI Integration**: Facilitate smooth and intelligent agent interactions using TogetherAI for a cohesive user experience.
- **Spring Boot Scalability**: Built on Spring Boot 3.3.3, ensuring a reliable and scalable backend architecture for seamless performance.
- **Dynamic Search and Recommendations**: Allow users to search for movies, actors, genres, or directors and receive personalized recommendations based on search results or user preferences.

## Tech Stack

- **Java 21**
- **SpringBoot 3.3.3**: Backend framework for building fast and scalable applications.
- **Together AI**: Provides models for describe image. Model: meta-llama/Meta-Llama-3.1-70B-Instruct-Turbo
- **Langchain4j**: Supercharge your Java application with the power of LLMs.

## Running On Local Machine (Linux):

1. Set up the following environment variables.
    - export PROJECT_ID=your_project_id;
    - export LOCATION=your_location;

2. Run the app by running `Application.java` in your IDE or `mvn` in the command line.

## Contributing

Feel free to open issues or submit pull requests to improve the project. Contributions are welcome!
