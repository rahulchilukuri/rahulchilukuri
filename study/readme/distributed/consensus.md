<!-- TOC start -->

- [Apache ZooKeeper](#apache-zookeeper)
  - [Overview](#overview)
  - [Key Features](#key-features)
  - [Architecture](#architecture)
  - [Use Cases](#use-cases)
  - [Conclusion](#conclusion)
- [Consensus Algorithms](#consensus-algorithms)
  - [Raft](#raft)
    - [Introduction](#introduction)
    - [Key Concepts](#key-concepts)
    - [Raft Components](#raft-components)
    - [Raft Algorithm Phases](#raft-algorithm-phases)
    - [Safety and Fault Tolerance](#safety-and-fault-tolerance)
    - [Use Cases](#use-cases-1)
    - [Conclusion](#conclusion-1)
  - [Paxos](#paxos)
    - [Introduction](#introduction-1)
    - [Problem Statement: Distributed Consensus](#problem-statement-distributed-consensus)
    - [Key Components of Paxos](#key-components-of-paxos)
    - [Paxos Algorithm Phases](#paxos-algorithm-phases)
    - [Key Properties of Paxos](#key-properties-of-paxos)
    - [Fault Tolerance:](#fault-tolerance)
    - [Applications of Paxos](#applications-of-paxos)
    - [Conclusion](#conclusion-2)

<!-- TOC end -->

<!-- TOC --><a name="apache-zookeeper"></a>
# Apache ZooKeeper

<!-- TOC --><a name="overview"></a>
## Overview
Apache ZooKeeper is a distributed coordination service designed to manage and synchronize large distributed systems. It provides a centralized service for maintaining configuration information, naming, providing distributed synchronization, and offering group services. ZooKeeper is widely used in distributed applications like Apache Hadoop, Apache Kafka, and Apache HBase.


<!-- TOC --><a name="key-features"></a>
## Key Features
Centralized Configuration Management: ZooKeeper allows distributed systems to store and manage configuration data centrally, ensuring consistency across all nodes.

Distributed Synchronization: It provides primitives like locks and barriers to synchronize processes across distributed systems.

Naming Service: ZooKeeper can be used as a naming registry where nodes can be registered and discovered dynamically.

Leader Election: It supports leader election mechanisms, which are crucial for distributed systems requiring a master node.

High Availability: ZooKeeper itself is designed to be highly available, with a quorum of servers ensuring that the service remains operational even if some servers fail.

Atomic Broadcast: ZooKeeper uses an atomic broadcast protocol to ensure that updates are propagated consistently across all servers.


<!-- TOC --><a name="architecture"></a>
## Architecture
ZooKeeper operates on a client-server model where clients connect to servers to read and write data. The servers form an ensemble, and data is replicated across all servers in the ensemble.

ZooKeeper Ensemble: A group of ZooKeeper servers that work together to provide the service. Typically, an ensemble consists of an odd number of servers (e.g., 3, 5) to maintain quorum.

ZNode: The fundamental data structure in ZooKeeper, similar to a file in a filesystem. Znodes can store data and have children, forming a hierarchical namespace.

Session: Clients establish a session with the ZooKeeper ensemble. The session remains active as long as the client sends heartbeats.

Watcher: Clients can set watches on znodes to receive notifications when the znode changes.

Data Model
ZooKeeper's data model is a hierarchical namespace similar to a filesystem. Each node in the namespace is called a znode. Znodes can be of two types:

Persistent Znodes: These znodes persist even after the client that created them disconnects.

Ephemeral Znodes: These znodes are automatically deleted when the client that created them disconnects.


<!-- TOC --><a name="use-cases"></a>
## Use Cases
Configuration Management: Centralized storage for configuration data that needs to be consistent across all nodes in a distributed system.

Leader Election: Coordinating the election of a leader in a distributed system to ensure that only one node acts as the master.

Distributed Locking: Implementing distributed locks to ensure that only one process can access a resource at a time.

Service Discovery: Allowing services to register themselves and be discovered by other services dynamically.

Cluster Management: Managing membership and state information for nodes in a distributed cluster.

Example: Leader Election with ZooKeeper
java
Copy
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LeaderElection implements Watcher {
    private static final String ZOOKEEPER_ADDRESS = "localhost:2181";
    private static final int SESSION_TIMEOUT = 3000;
    private static final String ELECTION_NAMESPACE = "/election";
    private ZooKeeper zooKeeper;
    private String currentZnodeName;

    public static void main(String[] args) throws IOException, InterruptedException {
        LeaderElection leaderElection = new LeaderElection();
        leaderElection.connectToZookeeper();
        leaderElection.volunteerForLeadership();
        leaderElection.electLeader();
        leaderElection.run();
        leaderElection.close();
        System.out.println("Disconnected from Zookeeper, exiting application");
    }

    public void connectToZookeeper() throws IOException {
        this.zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, this);
    }

    public void volunteerForLeadership() throws InterruptedException {
        String znodePrefix = ELECTION_NAMESPACE + "/c_";
        String znodeFullPath = zooKeeper.create(znodePrefix, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("znode name " + znodeFullPath);
        this.currentZnodeName = znodeFullPath.replace(ELECTION_NAMESPACE + "/", "");
    }

    public void electLeader() throws InterruptedException {
        Stat predecessorStat = null;
        String predecessorZnodeName = "";
        while (predecessorStat == null) {
            List<String> children = zooKeeper.getChildren(ELECTION_NAMESPACE, false);
            Collections.sort(children);
            String smallestChild = children.get(0);
            if (smallestChild.equals(currentZnodeName)) {
                System.out.println("I am the leader");
                return;
            } else {
                System.out.println("I am not the leader");
                int predecessorIndex = Collections.binarySearch(children, currentZnodeName) - 1;
                predecessorZnodeName = children.get(predecessorIndex);
                predecessorStat = zooKeeper.exists(ELECTION_NAMESPACE + "/" + predecessorZnodeName, this);
            }
        }
        System.out.println("Watching znode " + predecessorZnodeName);
    }

    public void run() throws InterruptedException {
        synchronized (zooKeeper) {
            zooKeeper.wait();
        }
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    System.out.println("Successfully connected to Zookeeper");
                } else {
                    synchronized (zooKeeper) {
                        System.out.println("Disconnected from Zookeeper event");
                        zooKeeper.notifyAll();
                    }
                }
                break;
            case NodeDeleted:
                try {
                    electLeader();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}


<!-- TOC --><a name="conclusion"></a>
## Conclusion
Apache ZooKeeper is a robust and reliable distributed coordination service that simplifies the development of distributed systems. Its ability to provide centralized configuration management, distributed synchronization, and leader election makes it an indispensable tool for building scalable and resilient distributed applications. By leveraging ZooKeeper, developers can focus on the core logic of their applications while relying on ZooKeeper to handle the complexities of distributed coordination.


<!-- TOC --><a name="consensus-algorithms"></a>
# Consensus Algorithms
<!-- TOC --><a name="raft"></a>
## Raft
<!-- TOC --><a name="introduction"></a>
### Introduction
The Raft consensus algorithm is a distributed consensus protocol designed to be easy to understand and implement. It was introduced by Diego Ongaro and John Ousterhout in their 2014 paper, "In Search of an Understandable Consensus Algorithm". Raft is widely used in distributed systems to ensure consistency across multiple servers, even in the presence of failures. This writeup provides a technical overview of Raft, its key components, and how it achieves consensus in distributed systems.

<!-- TOC --><a name="key-concepts"></a>
### Key Concepts
1. Consensus in Distributed Systems
Consensus is the process of agreeing on a single value or state among multiple nodes in a distributed system.

It is critical for ensuring data consistency and fault tolerance in systems like distributed databases, key-value stores, and replicated state machines.

2. Raft's Design Goals
Understandability: Raft was designed to be easier to understand and implement compared to other consensus algorithms like Paxos.

Safety: Ensures that the system behaves correctly even in the presence of failures.

Liveness: Guarantees that the system makes progress as long as a majority of nodes are operational.

<!-- TOC --><a name="raft-components"></a>
### Raft Components
Raft organizes nodes into three roles:

1. Leader
The leader is responsible for managing the log replication process.

It handles client requests, appends entries to its log, and ensures that other nodes (followers) replicate these entries.

There is only one leader at any given time.

2. Follower
Followers passively accept log entries from the leader.

They respond to requests from the leader and candidates.

If a follower does not hear from the leader, it can transition to a candidate.

3. Candidate
When a follower detects a leader failure (via an election timeout), it transitions to a candidate and starts a new leader election.

Candidates request votes from other nodes to become the new leader.

<!-- TOC --><a name="raft-algorithm-phases"></a>
### Raft Algorithm Phases
Raft operates in two main phases: Leader Election and Log Replication.

1. Leader Election
Election Timeout: Each follower starts a randomized election timeout. If it does not receive a heartbeat from the leader before the timeout expires, it becomes a candidate.

Requesting Votes: The candidate increments its term (a logical clock) and sends RequestVote RPCs to other nodes.

Voting: Nodes vote for the candidate if they have not already voted in the current term and the candidate's log is at least as up-to-date as their own.

Winning the Election: A candidate becomes the leader if it receives votes from a majority of nodes.

Leader Heartbeats: The new leader sends periodic heartbeats (AppendEntries RPCs with no log entries) to maintain its authority.

2. Log Replication
Client Requests: The leader appends client requests to its log as new entries.

Replication: The leader sends AppendEntries RPCs to followers to replicate the log entries.

Commitment: Once a log entry is replicated on a majority of nodes, the leader commits the entry and applies it to its state machine. It then notifies followers to do the same.

Consistency: Raft ensures that all committed entries are durable and consistent across nodes.

<!-- TOC --><a name="safety-and-fault-tolerance"></a>
### Safety and Fault Tolerance
1. Leader Completeness
Raft guarantees that a leader's log contains all committed entries from previous terms. This ensures consistency even after leader changes.

2. Election Safety
Only one leader can be elected per term, and a candidate must have the most up-to-date log to win an election.

3. Handling Failures
Leader Failure: If the leader fails, a new election is triggered, and a new leader is elected.

Network Partitions: Raft can tolerate network partitions as long as a majority of nodes can communicate and agree on a leader.

Log Repair: Followers with outdated logs are brought up-to-date by the leader during log replication.

Advantages of Raft
Simplicity: Raft's design is easier to understand and implement compared to Paxos.

Strong Consistency: Raft ensures strong consistency and fault tolerance.

Modularity: Raft separates leader election, log replication, and safety into distinct components, making it easier to reason about.

<!-- TOC --><a name="use-cases-1"></a>
### Use Cases
Raft is used in various distributed systems, including:

Etcd: A distributed key-value store used in Kubernetes.

Consul: A service mesh and distributed system for service discovery and configuration.

CockroachDB: A distributed SQL database.

<!-- TOC --><a name="conclusion-1"></a>
### Conclusion
Raft is a robust and understandable consensus algorithm that has become a popular choice for building distributed systems. Its clear separation of concerns, strong safety guarantees, and ease of implementation make it an excellent choice for ensuring consistency and fault tolerance in distributed environments. By leveraging leader election and log replication, Raft provides a reliable foundation for building scalable and 
resilient systems.
<!-- TOC --><a name="paxos"></a>
## Paxos
<!-- TOC --><a name="introduction-1"></a>
### Introduction
The Paxos consensus algorithm is a fundamental protocol in distributed systems, designed to achieve consensus among a set of nodes in the presence of failures. It was introduced by Leslie Lamport in 1989 and later published in a more accessible form in 1998. Paxos is widely used in distributed systems to ensure that a group of nodes can agree on a single value or a sequence of values, even in the face of network delays, partitions, and node failures.

This writeup provides a technical overview of the Paxos algorithm, its key components, and its operation.

<!-- TOC --><a name="problem-statement-distributed-consensus"></a>
### Problem Statement: Distributed Consensus
In a distributed system, consensus is the process of agreeing on a single value or a sequence of values among a group of nodes. The challenge lies in achieving this agreement despite:

Asynchronous communication: Messages may be delayed or reordered.

Node failures: Nodes may crash or become unresponsive.

Network partitions: The network may split, preventing communication between subsets of nodes.

Paxos addresses these challenges by providing a fault-tolerant mechanism to achieve consensus.

<!-- TOC --><a name="key-components-of-paxos"></a>
### Key Components of Paxos
Paxos operates through a series of rounds, each consisting of two phases: Prepare/Promise and Propose/Accept. The algorithm involves three roles:

Proposers: Propose values to be agreed upon.

Acceptors: Accept or reject proposed values.

Learners: Learn the agreed-upon value.

1. Proposers
Proposers are responsible for initiating the consensus process by proposing values. They coordinate with acceptors to ensure that a value is chosen.

1. Acceptors
Acceptors are responsible for accepting or rejecting proposals. They store the state of the system and ensure that only one value is chosen.

3. Learners
Learners observe the outcome of the consensus process and learn the chosen value.

<!-- TOC --><a name="paxos-algorithm-phases"></a>
### Paxos Algorithm Phases
The Paxos algorithm operates in two main phases:

Phase 1: Prepare/Promise
Prepare: A proposer selects a unique proposal number (higher than any it has used before) and sends a Prepare message to a majority of acceptors.

Promise: If an acceptor receives a Prepare message with a proposal number higher than any it has seen, it responds with a Promise message, indicating that it will not accept any proposal with a lower number. The acceptor also includes the highest-numbered proposal it has accepted (if any).

Phase 2: Propose/Accept
Propose: If the proposer receives Promise messages from a majority of acceptors, it sends an Accept message to those acceptors with a proposed value. The value is either:

The value from the highest-numbered proposal reported by the acceptors (if any).

A new value (if no acceptors reported any prior proposals).

Accept: If an acceptor receives an Accept message for a proposal number it has promised to consider, it accepts the value and sends an Accepted message to the proposer and learners.

Learning the Chosen Value
Once a majority of acceptors have accepted a value, the value is considered chosen. Learners learn the chosen value by receiving Accepted messages from the acceptors.

<!-- TOC --><a name="key-properties-of-paxos"></a>
### Key Properties of Paxos
Safety:

Agreement: No two nodes decide on different values.

Validity: Only proposed values can be chosen.

Termination: Eventually, a value is chosen (assuming no failures and sufficient communication).

Liveness:

Paxos guarantees progress as long as a majority of nodes are operational and can communicate.

<!-- TOC --><a name="fault-tolerance"></a>
### Fault Tolerance:

Paxos can tolerate up to f failures in a system of 2f + 1 nodes.

Variants of Paxos
Multi-Paxos: Optimizes Paxos for repeated consensus instances (e.g., in a log replication system) by electing a leader to streamline the process.

Fast Paxos: Reduces latency by allowing proposers to skip the Prepare phase under certain conditions.

Byzantine Paxos: Extends Paxos to handle Byzantine (arbitrary) failures.

Challenges and Limitations
Complexity: Paxos is notoriously difficult to understand and implement correctly.

Performance: The two-phase protocol can introduce latency, especially in large or geographically distributed systems.

Leader Election: Multi-Paxos requires a stable leader, which can be challenging in dynamic environments.

<!-- TOC --><a name="applications-of-paxos"></a>
### Applications of Paxos
Paxos is widely used in distributed systems, including:

Distributed databases: Google Spanner, Apache Cassandra.

Coordination services: Apache ZooKeeper, etcd.

Blockchain systems: Some consensus protocols are inspired by Paxos.

<!-- TOC --><a name="conclusion-2"></a>
### Conclusion
The Paxos algorithm is a cornerstone of distributed systems, providing a robust and fault-tolerant mechanism for achieving consensus. Despite its complexity, Paxos has proven to be a versatile and reliable solution for a wide range of applications. Understanding Paxos is essential for designing and implementing distributed systems that require strong consistency and fault tolerance.
