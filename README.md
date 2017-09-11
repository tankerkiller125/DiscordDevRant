# devRant Discord Bot

##### Running the Bot (Development)

Running the bot in development (Intellij)

1. Make a new Run configuration using the Kotlin template
2. Remove default "Before Run" task
3. Add Maven Build "Before Run" task
4. Set main class as `ml.rhodes.bots.discord.devrant.Main`
5. Set first program argument as the bot token
6. Set VM options to `-XX:+UnlockCommercialFeatures -XX:+FlightRecorder` if you want Java Mission Control Flight Recorder