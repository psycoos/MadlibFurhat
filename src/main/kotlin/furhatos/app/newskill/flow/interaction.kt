package furhatos.app.newskill.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.newskill.nlu.*
import furhatos.gestures.Gestures
import furhatos.gestures.Gestures.BigSmile
import furhatos.gestures.Gestures.BrowRaise
import furhatos.gestures.Gestures.ExpressSad
import furhatos.gestures.Gestures.Smile
import furhatos.nlu.common.Color
import furhatos.nlu.common.Number

val words: MutableList<String> = ArrayList()


fun Grounding(word: String): String {

    return "${word}, I will add it to the story"
}


val Start : State = state(Interaction) {

    onEntry {
        random(
                {   furhat.say("Hello") },
                {   furhat.say("Oh, hello welcome") },
                {   furhat.say("Hi") }
        )
        furhat.say("I am Furhat.")
        furhat.gesture(BigSmile(duration=2.0))
        delay(300)

        furhat.param.noSpeechTimeout = 10000 //set global default to trigger onNoResponse
        goto(AskToPlay)
    }
}

val AskToPlay = state(Interaction) {
    onEntry {
        random(
                {furhat.ask("Would you like to make a Mad lib with me")},
                {furhat.ask("Do you want to make a Mad lib?")}
        )
    furhat.gesture(BrowRaise)
    }

    onResponse<Yes>{
        random(
                {furhat.say("Great, let's start!")},
                {furhat.say("Awesome, let's go!")}
        )
        furhat.gesture(Gestures.Smile)

        furhat.say("A Madlib works as follows. I will ask you to come up with certain types of words. " +
                "Then I will use them to form a story.")

        goto(FirstName)
    }

    onResponse<No>{
        random(
                {furhat.say("Okay, well that's a shame. Have a nice day!")},
                {furhat.say("Oh, that's unfortunate. Have a nice day!")}
        )
        furhat.gesture(ExpressSad)
    }
}

val FirstName = state {
    onEntry {
        furhat.ask("Please give me a male first name")
        furhat.gesture(Gestures.Smile(strength=0.5))
    }

    onResponse<PersonName> {
        words.add(it.text)
        random(
            {furhat.say("${words[0]}, Sounds like a nice guy.")},
            {furhat.say("Alright, I will add ${words[0]} to the story.")}
        )
        goto(Profession)
    }
}

val Profession = state {
    onEntry {
        furhat.ask("Okay, then give me a profession.")
        furhat.gesture(BrowRaise)
    }

    onResponse<Professions> {
        words.add(it.text)
        random(
            {furhat.say("${words[1]}, my friend used to do that.")},
            {furhat.say("Ah, ${words[1]}, a noble profession")}
        )
        furhat.gesture(Smile)
        goto(Country)
    }
}

val Country = state {
    onEntry {
        furhat.ask("Please give me a country.")
    }

    onResponse<Countries> {
        words.add(it.text)
        if (it.text == "The Netherlands" || it.text == "the Netherlands") {
            furhat.say("Oh, ${words[2]}, I was made there!")
        } else {
            random(
                    { furhat.say("${words[2]}, nice place. I will add it to the story.") },
                    { furhat.say("Alright, ${words[2]} seems to be a popular holiday destination.") }

            )
        }
        goto(ColourOne)
    }
}

val ColourOne = state {
    onEntry {
        furhat.ask("What is your favourite color?")
    }

    onResponse<Color> {
        words.add(it.text)
        if (it.text == "Red" || it.text == "red") {
            furhat.say("${words[3]}, very romantic. My internal circuitry prevents me from feeling love. I will add it to the story.")
        }
        else if (it.text == "Blue" || it.text == "blue") {
            furhat.say("Oh ${words[3]}, Like water. I don't like water, it damages my internal organs.")
        }
        else if (it.text == "Green" || it.text == "green") {
            furhat.say("${words[3]}, like the grass outside. I can't go outside because i'm stuck inside this screen.")
        } else {
            furhat.say("${words[3]}, that's my favorite color! I will add it to the story.")
        }
        goto(Superpower)
    }
}

val Superpower = state {
    onEntry {
        furhat.ask("What superpower would you like to have?")
    }

    onResponse<Superpowers> {
        words.add(it.text)
        random(
                { furhat.say("${words[4]}, a bit boring but alright. I wil add it to the story.") },
                { furhat.say("${words[4]}, I wish I could do that, but I don't have a physical body. I will add it to the story though.") }
        )

        goto(Mammal)
    }
}

val Mammal = state {
    onEntry {
        furhat.ask("Can you give me the name of a mammal?")
    }

    onResponse<Mammals> {
        words.add(it.text)
        if (it.text == "Cat" || it.text == "cat" || it.text == "cats") {
            furhat.say("${words[5]}, a good friend of mine, Mariët, also has a very cute cat. I will add it to the story.")
        } else {
            furhat.say("${words[5]}, I will add it to the story.")
        }
        goto(ColourTwo)
    }
}

val ColourTwo = state {
    onEntry {
        furhat.ask("Please give me a number.")
    }

    onResponse<Number> {
        words.add(it.text)
        random (
                {furhat.say("${words[6]},That's the amount of transistors in my brain. I will add it to the story.")},
                {furhat.say("${words[6]},I hope that is enough, but I will add it to the story.")}
                )

        goto(FacialFeature)
    }
}

val FacialFeature = state {
    onEntry {
        furhat.ask("What about a facial feature?")
    }

    onResponse<FacialFeatures> {
        words.add(it.text)
        furhat.say("${words[7]}, some might say that is also my best feature. I will add it to the story.")
        goto(Vegetable)
    }
}

val Vegetable = state {
    onEntry {
        furhat.ask("What vegetable do you dislike the most?")
    }

    onResponse<Vegetables> {
        words.add(it.text)
        furhat.say("${words[8]}, those are especially disgusting. I will add it to the story.")
        goto(Capital)
    }
}

val Capital = state {
    onEntry {
        furhat.ask("Name a European capital.")
    }

    onResponse<Capitals> {
        words.add(it.text)
        if (it.text == "Enschede" || it.text == "enschede") {
            furhat.say("Oh, ${words[9]}, My good friend Mariët lives there")
        } else {
            furhat.say("${words[9]}, I heard it's beautiful there this time of year. I will add it to the story.")
        }
        goto(Weapon)
    }
}
val Weapon : State = state {
    onEntry {
        furhat.ask("Name some kind of weapon.")
    }

    onResponse<Weapon> {
        furhat.say("Come on! Can't you think of something more deadly?")
        goto(ProperWeapon)
    }
}
val ProperWeapon : State = state {
    onEntry {
        furhat.ask("Name some kind of weapon.")
    }

    onResponse<Weapon> {
        words.add(it.text)
        furhat.say("${words[10]}, That's better! I will add it to the story.")
        goto(TellStory)
    }
}


val TellStory = state {
    onEntry {
        furhat.say("Meet our hero ${words[0]},")
        furhat.gesture(BigSmile)
        furhat.say("a super intelligent ${words[1]}.")
        furhat.say("A run-in with the military of ${words[2]} leads him to create his superhero alter-ego")
        furhat.say("a ${words[3]} coloured giant with amazing powers, ")
        furhat.say("like ${words[4]}.")
        furhat.say("Every day, he battles bad guys with his pet ${words[5]}.")
        furhat.say("Eventually it is discovered that our hero's arch nemesis, Doctor Evil, distinguished by his ${words[6]} ${words[7]}s has turned ${words[8]} into a weapon.")
        furhat.say(" Leading to a huge battle between the two in downtown ${words[9]}, with slices of ${words[8]} falling from the sky.")
        furhat.say("Luckily, our hero is able to defeat him, capturing his enemy with his ${words[10]}.")
        furhat.say("So, our hero saves the day yet again.")

         goto(AnotherStory)
    }
}

val AnotherStory = state {
    onEntry {
        furhat.say("That was a great story!")
        furhat.ask("Do you want to try again?")
    }

    onResponse<Yes> {
        furhat.say("That's great!")
        furhat.gesture(Gestures.Smile)

        goto(FirstName)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, well that's a shame. Have a nice day!") },
                { furhat.say("Oh, that's unfortunate. Have a nice day!") }
        )
        furhat.gesture(ExpressSad)
    }

}


