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

* A replacement for conversations/emails/instant messaging - While it is an asynchronous communication tool, Reactivity aims more for _smarter_ communication between team members, management, and other teams. Various other communication channels may be used to accomplish that.

* **Highly Opinionated** - Reactivity doesn't and shouldn't strive to impose any particular process on the end users. Communication style is particular to each team, and tools that prove cumbersome are just as useless as unnecessary meetings.

* **Complex** - Many team tools seem to focus on too much and end up being too complex or having too many features. This can lead to users ignoring the tool's existence altogether. Reactivity has a narrow feature scope and purpose.

## Design Goals

* team-based status channels
* Immediate data presentation - looking at a channel should allow you to grok your team's situation immediately (what people are doing, or are blocked by).

## Example Use Case

Scenario:

1. mochify has three team members (alexkuang, birryree, himekat) and a stream called `mochify`.
2. At the top of the morning, it's status update time.
3. alexkuang opens the reactivity app and types in a status that goes to the mochify stream
