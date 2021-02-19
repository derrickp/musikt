# Musikt
This is an attempt to build a Kotlin wrapper around the Musicbrainz JSON API.

## Usage
If you want to go look up some recordings for an artist based on a song title.

```kotlin
import dev.plotsky.musikt.Configuration
import dev.plotsky.musikt.Client
import dev.plotsky.musikt.search.recordings.RecordingQuery

val config = Configuration(
    baseUrl = "https://musicbrainz.org/ws/2",
    appName = "Totally Awesome Music App",
    contact = "Your contact information"
)
val client = Client.build(config)
// artist is Artist Name, Title is a title of a song
val query = RecordingQuery(artist = "LIK", title = "Decay")
val recordings = client.recordings.getByQuery(query)
// Do stuff with the recording information!
```

There are other querying capabilities available on the `Client` for artists and areas as well. They all have a similar
where you can query by a Query object, an ID, or by just a general term.
