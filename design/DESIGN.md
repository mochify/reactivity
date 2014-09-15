# REACTIVITY: Design

## Goal of Reactivity?

Reactivity is supposed to be a stream-based communication system for sharing status and information between groups and teams. It is aimed at facilitating team-based communication (like with developers) and hopefully to replace things like status meetings, quick emails, and certain types of instant messaging.

## Nitty-gritty?

Right now I'm imagining multiple types of resources in Reactivity:

* Individuals - A collaborator
* Teams / Groups - A unit comprised of 0 or more Individuals
* Streams - A distinct communication channel either between individuals or teams

* Everything gets a unique identifier
* Individuals can be in zero or more teams and communicate with zero or more streams
* Messages can be unicast/broadcast/anycast into different streams that the individual has access to
* Streams can be ephemeral or permanent (as in they can have a lifetime), depending on setting
* Individuals have team-viewable statuses that can be set with specific phrases.

 The goal is to facilitate problem resolution like if someone is blocked by an issue, work ticket, or if they are waiting on someone else.

 For example, let's say Alice, Bob, and Eve work on the same team. Alice and Bob are developers, and Eve is a project manager. They typically run a morning standup meeting to give their updates.

 In the Reactivity world, the standup meeting is a time sink that can be done away with and replaced with Reactivity Streams.

### Example Reactivity Usage

Project Team Stream - Alice, Bob, Eve are team members. Jim is another member of the organization who is outside of the team. The following could be messages sent from each person.

Alice - WORKING on tickets ISSUE-01, ISSUE-02, ISSUE-03
         BLOCKED by @Bob on ISSUE-03
Bob - WAITING on requirements from @Jim.
Eve - WORKING on documentation.

Reactivity's stream would set statuses like so:

* Alice is working on tickets ISSUE-01, ISSUE-02
* Alice is blocked by @Bob on ISSUE-03
* Bob is waiting on requirements from @Jim -> @Jim is working on ISSUE-45 (if Jim's status is publicly set)
* Eve is working on documentation.

Team TODOs

* Unblock Alice on ISSUE-03
* Get requirements to Bob from @Jim

### JSON format

{
    "sender": "a user ID"
    "update": "a long message",

}


* Each team gets a unique identifier and a person can be part of more than one team
* You can POST a status via a JSON message (format yet to be defined).
    * You can specify that the message is broadcast to all channels or just to a specific team
* The status will be the most recent reflected for you for the team stream.
* The team stream displays the latest statuses from everyone, and can list problems and work.
