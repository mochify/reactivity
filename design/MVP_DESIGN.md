# Reactivity POC

This document lays out the goals, features, design and high-level implementation of Reactivity v0.1, which from henceforth will be called the POC (*proof of concept*).

## What Reactivity Is

At its most basic, Reactivity is an asynchronous communication tool for teams, focused on replacing the need for status meetings and updates.

Far too often, developers and management fall into the trap of "5 minute standup that becomes 60 minute design meeting." This is an inefficient use of a team's time, due to many reasons:

* **Decreased Productivity** - Meetings are inherently disruptive to normal workflows, as people, especially developers, focus on getting into a zone of higher concentration. Meetings can lead to more than just several minutes of productivity loss for the team.

* **Loss of Information** - Status meetings can be very information-dense, especially if they suddenly evolve into larger meetings (meeting scope creep). Information in the status meetings tend to become ignored or forgotten quickly.

* **Confusion and Low Team Morale** - Ineffective meetings can lead to team dissatisfaction, and confusion over their purpose.

Reactivity aims to fix those problems by providing a tool that can be adapted to a team's particular communication style, and allow team members to focus more on their work. Additionally, it aims to encourage improved asynchronous communication inside and between teams.

## What Reactivity Isn't

It's very important to understand what Reactivity _isn't_:

* **A replacement for conversations/emails/instant messaging** - While it is an asynchronous communication tool, Reactivity aims more for _smarter_ communication between team members, management, and other teams. Various other communication channels may be used to accomplish that.

* **Highly Opinionated** - Reactivity doesn't and shouldn't strive to impose any particular process on the end users. Communication style is particular to each team, and tools that prove cumbersome are just as useless as unnecessary meetings.

* **Complex** - Many team tools seem to focus on too much and end up being too complex or having too many features. This can lead to users ignoring the tool's existence altogether. Reactivity has a narrow feature scope and purpose.

## Design Goals

* quick access dashboards
* team-based status channels
* simplicity
* Immediate data presentation - looking at a channel should allow you to grok your team's situation immediately (what people are doing, or are blocked by).

## Technical Details

Reactivity's basic tech stack is as follows:

* react.js/HTML5/CSS3 - the standard front-end stuff
* Clojure (and/or Scala) on Java 8 - The JVM is mochify's semi-official platform for web services
  * `compojure` will serve as the routing library (or Scalatra)
  * `liberator` will expose the endpoints as RESTful resources
  * `friend` will provide authentication and authorization
  * `yesql` will provide the SQL functionality (it is *not* an abstraction layer)
  * `carmine` will provide the Redis interaction
* Databases
  * PostgreSQL - will store all persistent data, like `users` and `statuses`
  * Redis - intermediate caching layer and session store
  * Others? Maybe.

## Database Model

Reactivity has the following dimensions:

* Users - an actor, a person, someone that posts messages and statuses
* Channels - A collection of statuses
* Statuses - like a tweet, these are associated with a channel, and are created by users
* Comments - A message attached to a specific status in a channel. These are much simpler than full-fledged comment systems

![Database schema](https://github.com/mochify/reactivity/blob/master/resources/models/mvp_schema.png)

## Software Model


## API Model

Like a good web app, RESTful is there.

* `api/users` - Look up a user
* `api/comments` - Look up comments
* `api/statuses`
* `api/channels`




## Example Use Case

Scenario:

1. mochify has three team members (alexkuang, birryree, himekat) and a channel called `mochify`.
2. At the top of the morning, it's status update time.
3. alexkuang opens the reactivity app and types in a status that goes to the mochify stream
