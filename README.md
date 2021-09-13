# GeoNote
An extension for Trimble's Precision-IQ(PIQ) Android application
Created for CS@Mines Field Session

# Background
Trimble Agriculture's Precision-IQ(PIQ) application is designed to assist farmers in operating various farm equipment. This includes automated driving and application control. See Trimble Ag's website for more information.

# Description

GeoNotes are data structures representing a note at a specific location.

A GeoNote contains:
* An icon representing the type of note
* A type: trash, animal, warning, etc.
* Location information
* An optional picture from the attached camera

GeoNotes can be created from the PIQ runscreen and viewed on the GeoNote Extension Page.
The Extension Page displays the current location of the vehicle and any GeoNotes that have been created.
GeoNotes are drawn on the map and in a side bar allowing to inflate either for more information.
# Usage
You will need to generate a MapBox access token before using the software. See Mapbox's JavaScript website for more information.
Load the application onto a Trimble tablet with Android Studio and them enable it with the extension manager.

# Future Work
Due to the lack of time some features were not implemented fully. Audio recording was left out, but the frame work exists for it. The map section could be better, currently there is not a way to remove GeoNotes from the map without reopening the page and they do not have unique icons. The popups could also be expanded to show pictures and audio if necessary.
# Credit
* [@Elijah Mt. Castle](https://github.com/Elijah1111)
* [@jacks0nwill](https://github.com/jacks0nwill)
* [@ConBil16](https://github.com/ConBil16)
* [@jksimpson13](https://github.com/jksimpson13)
* [Trimble](https://agdeveloper.trimble.com/) (Splice API and Precision-IQ)
* [MapBox](https://www.mapbox.com/)
