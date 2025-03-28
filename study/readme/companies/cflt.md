# IQue

1. Implement a LRU/MRU Cache
2. Find if a sudoku is valid and also find the correct solution for it.
3. Regex pattern matching along with java concurrenty
4. Java concurrency using threads type problem
5. Matrix path finding problem with weigths in each cells
6. Behaviour style round with hiring manager Be well prepared with leetcode medium and hard problems.
7. Tell me about a challenging project that you worked on.
8. Meeting Room II in LeetCode.
9. windowed hashmap design. Windowed Average problem (you would need to implement a cache where each item has an expiration time, you would also need to return the average of the valid elements in the cache at any given point time
10. Dijkstra Algorithm, threads, semaphores, mutex
11. Create a data structure that can perform CRUD operations on data coming in a time period
12. wildcard pattern matching using Dynamic Programming(Given a string and a pattern with wildcard, return true if the pattern matches the string)
13.  One Distributed system design and one Linux/K8s
14.  Two of the technical interviews were system design and architecture questions. It is meant to test your knowledge of distributed systems, databases, replication, caching, etc.
15.  Make sure you are current on threading/concurrency, data structures (trees especially), and other algorithmic types of problems
16.  URL shortener
17.  Involves implementing a multi-threaded data structure. I highly recommend revising up on Locks, Condition Variables and other language primitives to starting on this
18.  number queue with avg

Glassdoor questions:

- Given a game to you which is running on an instance and hasMySQL installed on it locally, now with the game popularity increasing, suggest ways that it stays highly secure and highly available and then with every step he was adding more things on it, like we want to use JWT on it, should we use it? session maintenance etc.
- URL shortener in Go
- MRU cache implementation in java or Go
- Distributed Systems, Coding Comprehension 
- garbage collection in Java
- SFDC related experience
- debounce function
- Memory management for Java applications
- serialise a binary tree?
- LRU Cache
- Count trailing zeroes in factorial of a number


Hackernews questions:

- How do you design a CDN form scratch?
- How would you design a Distributed hash table?
- Experience with `go trace` to debug memory leak in Go
- Analyze memory footprint of a class in Java vs. Go struct
- Garbage collection pauses in Java JVM are common, we can set a soft limit for pauses: -XX:MaxGCPauseMillis=200
- Go uses concurrent garbage collection that runs concurrently with the main thread:
  > initially introduced 2014: https://github.com/golang/go/issues/9477
  > incorprated into Go compiler 2016: https://go-review.googlesource.com/c/go/+/23540
  Also Go compiler unlike Java compiler favors more heap than stack so less memory footprint overall. Remember GC runs on heap and not 
  stack and stack memory doesn't get de-allocated once its allocated at the beginning of the program.

GitHub questions:
- Variant of Run-length encoding
- Minimum Number of Platforms Required for a Railway/Bus Station (basic scheduling for distributed systems)

# IQue LC
## q1
First round was technical - Windowed Average problem (you would need to implement a cache where each item has an expiration time, you would also need to return the average of the valid elements in the cache at any given point time.)

Design round 1(API design) - Spotify like system. Design APIs to retrieve X podcast channels at a time, X audio clips per podcast channel, Database design and high level component design [pagination, skip tokens, limit/offset, api versioning, back of the napkin calculation for number of users to number of podcast channels, podcast channel to number of audio clips per channel, corner cases[users subscribed to a lot of channels, channels with a lot of videos, etc.,]]

Coding round: Given a filled sudoku board, validate if the board is solved or not [optimize the number of traversals through the board - attempt at solving in 1 traversal] - Add test cases and make sure it runs and passes.
As an extension, given an unsolved board how do you solve it (backtracking with recursion - did not have to implement this)

Design round: TinyUrl (Constraints: optimize only for high volume of reads - a simple singleton service with a sharded database(range based sharding on short url would suffice. You can add more read replicas with additional caching at the edge if needed). Do not make it complex

Behavior - Talk about a project you are most proud of, understand the technical details, the business use case. Talk about the challenges, conflicts that came about, etc.,

Coding - Given a list of documents [Pair of id and content], return the list of documentIds for a given search phrase. Dictionary of documentId to dictionary of string and their index - Use this to then check for a given search phrase.

## q2
1. Windowed average
2. Url shortener
3. Task scheduler
4. Feed generation(rss feeds, focus on apis)

# Cloud Networking and Security: A Brief Technical Writeup
Cloud networking and security are intertwined disciplines crucial for organizations leveraging cloud computing. They address the challenges of extending traditional network and security paradigms to the dynamic and distributed nature of cloud environments.

## Cloud Networking:

Cloud networking encompasses the infrastructure and services that enable connectivity and communication within and between cloud resources, as well as between the cloud and on-premises environments. Key aspects include:

Virtual Networks (VNETs/VPCs): Software-defined networks isolated within a cloud provider's infrastructure, offering logical boundaries for resources. These allow organizations to define their own IP address spaces, subnets, routing tables, and network gateways.
Load Balancing: Distributing incoming traffic across multiple instances to improve application availability, performance, and fault tolerance. Cloud providers offer various load balancing services (e.g., application, network, internal).
DNS Services: Providing scalable and reliable name resolution for cloud resources.
Content Delivery Networks (CDNs): Distributed networks of servers that cache content closer to users, reducing latency and improving website and application performance.
Hybrid Connectivity: Establishing secure and reliable connections between on-premises infrastructure and cloud environments using technologies like VPNs (Virtual Private Networks) and dedicated connections (e.g., AWS Direct Connect, Azure ExpressRoute, Google Cloud Interconnect).
Network Segmentation: Dividing the cloud network into isolated segments to limit the blast radius of security incidents and enforce granular access control.
Traffic Management: Implementing policies and controls to manage network traffic flow, prioritize certain types of traffic, and optimize network performance.
Cloud Security:

Cloud security focuses on protecting cloud-based assets, data, applications, and infrastructure from threats. It's a shared responsibility model, where the cloud provider secures the underlying infrastructure, and the customer is responsible for securing what they put in the cloud. Key areas include:

Identity and Access Management (IAM): Controlling who can access what resources and what actions they can perform. This involves authentication (verifying identity) and authorization (granting permissions). Cloud providers offer robust IAM services with features like multi-factor authentication (MFA), role-based access control (RBAC), and privileged access management (PAM).
Data Security: Protecting data at rest, in transit, and in use. This involves encryption, data masking, tokenization, and data loss prevention (DLP) strategies. Cloud providers offer various encryption options and services to help organizations manage their data securely.
Network Security: Implementing security controls at the network level to prevent unauthorized access and malicious activity. This includes:
Firewalls (Network Security Groups/Security Groups): Filtering inbound and outbound traffic at the instance level or subnet level based on defined rules.
Intrusion Detection and Prevention Systems (IDPS): Monitoring network traffic for malicious patterns and automatically blocking or alerting on suspicious activity.
Web Application Firewalls (WAFs): Protecting web applications from common web exploits like SQL injection and cross-site scripting (XSS).
Threat Detection and Response: Implementing tools and processes to identify, analyze, and respond to security threats in the cloud environment. This includes security information and event management (SIEM) systems, threat intelligence feeds, and automated response mechanisms.
Compliance and Governance: Adhering to relevant industry regulations and security standards. Cloud providers often offer compliance certifications and tools to help customers meet their obligations.
Security Automation: Leveraging automation to streamline security tasks, improve efficiency, and reduce the risk of human error. This includes automated vulnerability scanning, configuration management, and incident response.
Interrelation:

Cloud networking forms the foundation upon which cloud security controls are implemented. Secure network configurations, proper segmentation, and controlled traffic flow are essential for enforcing security policies and limiting the impact of breaches. Conversely, security considerations must be integrated into the design and deployment of cloud networking infrastructure to ensure confidentiality, integrity, and availability.

## Challenges:

Securing and managing cloud networks presents unique challenges:

Complexity: Cloud environments can be highly complex and dynamic, making it difficult to maintain visibility and control.
Shared Responsibility: Understanding and adhering to the shared responsibility model is crucial to avoid security gaps.
Evolving Threats: The threat landscape is constantly evolving, requiring continuous adaptation of security measures.
Skills Gap: Organizations may face a shortage of professionals with the necessary cloud networking and security expertise.
Integration: Integrating cloud security tools and processes with existing on-premises security infrastructure can be challenging in hybrid environments.

## Conclusion:

Effective cloud networking and security are paramount for organizations to realize the full potential of cloud computing while mitigating risks. A well-defined strategy that incorporates secure network design principles, robust security controls, and a clear understanding of the shared responsibility model is essential for building and maintaining a secure and resilient cloud environment. Continuous monitoring, proactive threat management, and ongoing adaptation to the evolving cloud landscape are critical for long-term success.





# API Life Cycle Management in the context of APIGateway
API Life Cycle Management (ALM) encompasses all the stages an API goes through from its initial conception to its eventual retirement. An API Gateway plays a central and critical role in managing and enforcing policies throughout this lifecycle. It acts as a control point for all API traffic, enabling consistent management and security across diverse APIs.

Here's a breakdown of how an API Gateway facilitates different stages of the API lifecycle:

## Design & Development:

API Definition & Specification: While the API Gateway itself doesn't design the API, it often integrates with or leverages API definition standards like OpenAPI (Swagger). This allows developers to define the API contract (endpoints, methods, request/response schemas) which the Gateway can then understand and enforce.
Mocking & Testing: Some API Gateways offer mocking capabilities based on the API definition. This allows frontend developers and consumers to start working with a simulated API even before the backend is fully implemented. This early feedback loop can improve API design.
Developer Portal Integration: API Gateways often power developer portals, providing documentation, interactive consoles, and code samples based on the API definition. This aids in developer onboarding and understanding the API.
## Deployment & Publishing:

Endpoint Management: The API Gateway acts as a single entry point for all backend services. It decouples API consumers from the underlying service locations, allowing backend services to be deployed and scaled independently without impacting consumers.
Routing & Orchestration: The Gateway handles routing incoming requests to the appropriate backend service based on defined rules. More advanced Gateways can also orchestrate calls to multiple backend services and aggregate the responses.
Version Management: API Gateways are crucial for managing different versions of an API. They allow organizations to deploy new versions alongside older ones, enabling a smooth transition for consumers. Routing rules can direct traffic to specific versions based on headers, paths, or other criteria.
Environment Management: Gateways can be configured to manage APIs across different environments (e.g., development, staging, production). This often involves different configurations, security policies, and backend endpoints.
## Management & Monitoring:

Traffic Control & Rate Limiting: API Gateways enforce policies to manage traffic flow, prevent abuse, and ensure fair usage. This includes rate limiting, throttling, and quota management.
Security: This is a core function. The Gateway handles authentication (verifying the identity of the consumer) and authorization (determining what the consumer is allowed to do). It can enforce various security mechanisms like API keys, OAuth 2.0, JWT validation, and IP whitelisting/blacklisting.
Monitoring & Analytics: API Gateways provide valuable insights into API usage, performance, and errors. They can track metrics like request volume, latency, error rates, and identify potential issues. This data is crucial for debugging, optimization, and understanding API adoption.
Logging & Auditing: The Gateway logs API requests and responses, providing an audit trail for security and troubleshooting purposes.
Transformation & Mediation: Gateways can transform request and response formats (e.g., XML to JSON, protocol conversion) to ensure compatibility between consumers and backend services. They can also enrich requests or responses with additional data.
Caching: Implementing caching at the API Gateway level can significantly improve performance and reduce load on backend services by storing frequently accessed responses.
## Retirement & Deprecation:

Deprecation Management: The API Gateway can play a role in managing the deprecation process by providing warnings to consumers accessing older versions or by gradually phasing out access.
Traffic Redirection: When an API version is retired, the Gateway can be configured to redirect traffic to a newer version or provide a graceful shutdown message.
Policy Removal: Once an API is fully retired, the corresponding configurations and policies within the API Gateway can be removed.
Key Benefits of Using an API Gateway for ALM:

Centralized Control: Provides a single point for managing and enforcing policies across all APIs.
Consistency: Ensures consistent security, monitoring, and traffic management across the API landscape.
Decoupling: Decouples API consumers from backend services, enabling greater flexibility and agility.
Improved Security: Offers a robust layer of security to protect backend services from unauthorized access and malicious attacks.
Enhanced Observability: Provides valuable insights into API usage and performance.
Simplified Management: Streamlines the management of various API versions and environments.

##Conclusion
An API Gateway is an indispensable component for effective API Life Cycle Management. It acts as the gatekeeper and policy enforcer, ensuring that APIs are designed, deployed, managed, secured, and retired in a controlled and consistent manner. By leveraging the capabilities of an API Gateway, organizations can significantly improve the reliability, security, and manageability of their API ecosystem.


# Comparison
Nginx vs Envoy vs Traefik vs HAProxy vs Kong vs Istio vs Knative


You've listed a powerful set of technologies that play crucial roles in modern application delivery, particularly in cloud-native environments. However, it's important to understand that they aren't all direct competitors and often serve different primary purposes, although there can be overlap in functionality.

Here's a breakdown of each, highlighting their core strengths and typical use cases:

## Nginx:

Primary Role: High-performance web server, reverse proxy, load balancer, HTTP cache, and mail proxy.
Strengths:
Performance: Extremely fast and efficient in handling concurrent connections and serving static content.
Stability: Proven and reliable in production environments for years.
Versatility: Can be used for a wide range of tasks, from basic web serving to complex reverse proxy configurations.
Large Community & Ecosystem: Extensive documentation, modules, and community support.
Relatively Simple Configuration: While powerful, its core configuration is generally straightforward.
Typical Use Cases:
Web server for static and dynamic content.
Reverse proxy in front of application servers.
Load balancer for distributing traffic across multiple backend instances.
HTTP caching to improve performance.
Ingress controller for Kubernetes (though less cloud-native focused than others).
## Envoy:

Primary Role: Modern, high-performance, L7 proxy and communication bus designed for cloud-native applications.
Strengths:
Cloud-Native Focus: Built with microservices and dynamic environments in mind.
Dynamic Configuration: Can be reconfigured without restarts through an API (xDS).
Extensibility: Highly extensible through a rich filter chain for various functionalities (e.g., traffic management, security, observability).
Observability: First-class support for metrics, tracing, and logging.
Service Discovery Integration: Integrates well with various service discovery mechanisms.
Advanced Traffic Management: Offers sophisticated routing, traffic splitting, and fault injection capabilities.
Typical Use Cases:
Service mesh data plane (as the proxy sidecar in Istio, Consul Connect, etc.).
Standalone edge proxy or API gateway in cloud-native environments.
Modern load balancer with advanced features.
## Traefik:

Primary Role: Modern, dynamic reverse proxy and load balancer designed for microservices and container orchestration platforms.
Strengths:
Automatic Configuration: Automatically discovers and configures itself based on the underlying infrastructure (e.g., Docker, Kubernetes, Consul).
Ease of Use: Known for its relatively simple configuration and automatic service discovery.
Cloud-Native Integration: Excellent support for container orchestration platforms.
Let's Encrypt Integration: Easy automatic TLS certificate management.
Metrics and Monitoring: Provides built-in metrics and integrates with monitoring systems.
Typical Use Cases:
Ingress controller for Kubernetes and other container orchestrators.
Reverse proxy for microservices deployed in containers.
Load balancer for dynamic backend services.
## HAProxy:

Primary Role: High-performance and reliable load balancer and proxy server.
Strengths:
Performance: Known for its exceptional speed and low resource consumption.
Stability: Highly mature and battle-tested in demanding production environments.
Versatility: Supports various protocols (TCP, HTTP, HTTPS) and load balancing algorithms.
Rich Feature Set: Offers advanced health checks, session persistence, and traffic manipulation capabilities.
Scripting (Lua): Allows for custom logic and extensions.
Typical Use Cases:
High-performance load balancing for web applications and other services.
TCP and HTTP proxying.
Database load balancing.
## Kong:

Primary Role: API Gateway built on top of Nginx (or now also supporting other data planes).
Strengths:
Plugin Ecosystem: Extensive library of pre-built plugins for authentication, rate limiting, request transformation, logging, monitoring, and more.
Extensibility: Allows for custom plugin development.
API Management Features: Provides features beyond basic routing, such as developer portals, analytics, and API versioning (often through plugins).
Scalability: Designed for horizontal scaling.
Declarative Configuration (Kong Gateway 3.0+): Supports GitOps workflows.
Typical Use Cases:
Centralized API gateway for managing and securing APIs.
Implementing cross-cutting concerns (authentication, logging, etc.) for multiple APIs.
Exposing backend services as managed APIs.
## Istio:

Primary Role: Open-source service mesh that provides a way to connect, secure, control, and observe microservices.
Strengths:
Comprehensive Service Mesh Features: Offers traffic management, security (mTLS), observability, and policy enforcement across a network of microservices.
Traffic Management: Advanced routing rules, traffic splitting (canary deployments), fault injection, and retries.
Security: Automatic mutual TLS (mTLS), strong identity, and policy enforcement.
Observability: Automatic metrics, distributed tracing, and logging.
Policy Enforcement: Fine-grained control over service access and behavior.
Platform Agnostic (to some extent): Primarily focused on Kubernetes but can be deployed in other environments.
Typical Use Cases:
Managing complex microservices architectures.
Implementing zero-trust security between services.
Enabling advanced traffic management for deployments and testing.
Gaining deep observability into service behavior.
## Knative:

Primary Role: Kubernetes-based platform to build, deploy, and manage serverless, event-driven applications.
Strengths:
Serverless Abstraction: Simplifies the deployment and management of containerized applications by abstracting away underlying infrastructure.
Autoscaling (Scale-to-Zero): Automatically scales applications up and down based on demand, including scaling down to zero when idle.
Traffic Management: Provides features for routing, traffic splitting, and revisions.
Eventing: Enables event-driven architectures.
Build Automation: Offers components for building container images from source code.
Typical Use Cases:
Deploying and managing serverless functions and applications on Kubernetes.
Building event-driven systems.
Rapid prototyping and deployment of containerized workloads.
Key Differences and Overlaps:

Level of Abstraction: Knative provides a higher level of abstraction for serverless workloads, while Istio focuses on service-to-service communication within a mesh.
Primary Focus: Nginx and HAProxy are primarily focused on performance and reliability as web servers and load balancers. Envoy is designed for cloud-native proxying and service mesh. Traefik emphasizes ease of use and automatic configuration in containerized environments. Kong builds on a proxy (initially Nginx) to offer a comprehensive API gateway with plugin capabilities. Istio is a full-fledged service mesh.
Cloud-Native vs. Traditional: Envoy, Traefik, Istio, and Knative are heavily geared towards cloud-native and containerized environments. Nginx and HAProxy have strong roots in more traditional infrastructure but are also widely used in cloud environments. Kong bridges this gap by providing API management features suitable for modern architectures.
Service Mesh vs. API Gateway: Istio is a service mesh managing traffic between services within a cluster. Kong is an API gateway managing traffic from outside the cluster to your services. While there can be some overlapping functionalities (e.g., rate limiting, authentication), their primary scopes differ. Envoy is often the data plane for service meshes like Istio.
When to Choose Which:

Simple Web Serving or Basic Reverse Proxy: Nginx is often the go-to choice for its simplicity and performance.
High-Performance Load Balancing: HAProxy is renowned for its speed and reliability.
Cloud-Native Proxying and Service Mesh Data Plane: Envoy is a strong contender, especially when using a service mesh control plane like Istio or Consul Connect.
Automatic Configuration in Containerized Environments: Traefik excels at simplifying ingress and reverse proxy setup in Docker and Kubernetes.
Comprehensive API Management: Kong provides a rich set of features for managing and securing APIs through its plugin ecosystem.
Microservices Management with Advanced Traffic Control, Security, and Observability: Istio offers a comprehensive service mesh solution.
Serverless Workloads on Kubernetes: Knative simplifies the deployment and scaling of serverless applications.
In many real-world scenarios, these technologies are used in conjunction. For example, you might use Nginx or HAProxy as an ingress controller in front of a Kubernetes cluster, with Istio managing the traffic within the cluster, and Kong acting as an API gateway for external access. Understanding their individual strengths and how they can complement each other is key to building robust and scalable applications.
