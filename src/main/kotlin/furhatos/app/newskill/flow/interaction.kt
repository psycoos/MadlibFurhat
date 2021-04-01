package furhatos.app.newskill.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.newskill.nlu.*
import furhatos.gestures.Gestures
import furhatos.gestures.Gestures.BigSmile
import furhatos.gestures.Gestures.ExpressSad
import furhatos.nlu.common.Color
import furhatos.nlu.common.Number

val words: MutableList<String> = ArrayList()

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

        goto(AskToPlay)
    }
}

val AskToPlay = state(Interaction) {
    onEntry {
        random(
                {furhat.ask("Would you like to make a Mad lib with me")},
                {furhat.ask("Do you want to make a Mad lib?")}
        )

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
        furhat.gesture(Gestures.ExpressSad)
    }
}

val FirstName = state {
    onEntry {
        furhat.ask("Please give me a male first name")
        furhat.gesture(Gestures.Smile(strength=0.5))
    }

    onResponse<PersonName> {
        words.add(it.text)
        furhat.say("${words[0]}, I will add it to the story.")
        goto(Profession)
    }
}

val Profession = state {
    onEntry {
        furhat.ask("Okay, then give me a profession.")
    }

    onResponse<Professions> {
        words.add(it.text)
        furhat.say("${words[1]}, I will add it to the story.")
        goto(Country)
    }
}

val Country = state {
    onEntry {
        furhat.ask("Please give me a country.")
    }

    onResponse<Countries> {
        words.add(it.text)
        furhat.say("${words[2]}, I will add it to the story.")
        goto(ColourOne)
    }
}

val ColourOne = state {
    onEntry {
        furhat.ask("What is your favourite color?")
    }

    onResponse<Color> {
        words.add(it.text)
        furhat.say("${words[3]}, I will add it to the story.")
        goto(Superpower)
    }
}

val Superpower = state {
    onEntry {
        furhat.ask("What superpower would you like to have?")
    }

    onResponse<Superpowers> {
        words.add(it.text)
        furhat.say("${words[4]}, I will add it to the story.")
        goto(Mammal)
    }
}

val Mammal = state {
    onEntry {
        furhat.ask("Can you name an animal that belongs to the mammals?")
    }

    onResponse<Mammals> {
        words.add(it.text)
        furhat.say("${words[5]}, I will add it to the story.")
        goto(ColourTwo)
    }
}

val ColourTwo = state {
    onEntry {
        furhat.ask("Please give me another color.")
    }

    onResponse<Number> {
        words.add(it.text)
        furhat.say("${words[6]}, I will add it to the story.")
        goto(FacialFeature)
    }
}

val FacialFeature = state {
    onEntry {
        furhat.ask("What about a facial feature?")
    }

    onResponse<FacialFeatures> {
        words.add(it.text)
        furhat.say("${words[7]}, I will add it to the story.")
        goto(Vegetable)
    }
}

val Vegetable = state {
    onEntry {
        furhat.ask("What vegetable do you dislike the most?")
    }

    onResponse<Vegetables> {
        words.add(it.text)
        furhat.say("${words[8]}, I will add it to the story.")
        goto(Capital)
    }
}

val Capital = state {
    onEntry {
        furhat.ask("Name an European capital.")
    }

    onResponse<Capitals> {
        words.add(it.text)
        furhat.say("${words[9]}, I will add it to the story.")
        goto(Weapon)
    }
}

val Weapon = state {
    onEntry {
        furhat.ask("Name some kind of weapon.")
    }

    onResponse<Weapon> {
        words.add(it.text)
        furhat.say("${words[10]}, I will add it to the story.")
        goto(TellStory)
    }
}


val TellStory = state {
    onEntry {
        furhat.say("Meet our hero ${words[0]},")
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

//        goto(FirstName)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, well that's a shame. Have a nice day!") },
                { furhat.say("Oh, that's unfortunate. Have a nice day!") }
        )
        furhat.gesture(ExpressSad)
    }

}


