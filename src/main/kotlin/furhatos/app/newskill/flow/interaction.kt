package furhatos.app.newskill.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.newskill.nlu.*
import furhatos.gestures.Gestures
import furhatos.gestures.Gestures.BigSmile
import furhatos.gestures.Gestures.BrowRaise
import furhatos.gestures.Gestures.Smile
import furhatos.gestures.Gestures.Nod
import furhatos.gestures.Gestures.Oh
import furhatos.gestures.Gestures.Wink
import furhatos.gestures.Gestures.BrowFrown
import furhatos.gestures.Gestures.Surprise
import furhatos.gestures.Gestures.ExpressFear
import furhatos.gestures.Gestures.ExpressDisgust
import furhatos.nlu.common.Color
import furhatos.nlu.common.Number

val words: MutableList<String> = ArrayList()


fun Grounding(word: String): String {

    return "${word}, I will add it to the story"
}


val Start : State = state(Interaction) {

    onEntry {
        furhat.gesture(Gestures.Smile)
        random(
                {  furhat.say{
                    +"Hi there, "
                    +Gestures.Smile
                    +"nice to meet you."} },
                {   furhat.say{
                    +"Oh, "
                    +Gestures.Oh
                    +"hello."} },
                {   furhat.say("Welcome.") }
        )
        delay(200)
        furhat.say("I am Furhat.")
        delay(200)
        furhat.say("You are about to create an amazing story with me")
        furhat.gesture(Gestures.Smile, async=false)
        goto(Explain)
    }
}

val Explain = state(Interaction) {
    onEntry{
        furhat.ask("Do you know what a Mad Lib is?")
        furhat.gesture(Gestures.BrowRaise)
        furhat.param.noSpeechTimeout = 20000 //set global default to trigger onNoResponse
    }

    onResponse<Yes>{
        furhat.gesture(Gestures.Smile)
        furhat.say("Great!")
        goto(AskToPlay)
    }

    onResponse<No>{
        furhat.gesture(Gestures.Nod(duration=0.5), async = false)
        delay(200)
        furhat.say("A mad lib is a word game where we will make a story together")
        furhat.say("There are still blanks in the story, so you will have to help me to fill them in.")
        furhat.gesture(Gestures.Smile)
        delay(300)
        goto(AskToPlay)
    }
}

val AskToPlay = state(Interaction) {
    onEntry {
        random(
                {furhat.ask("So, would you like to make a Mad lib with me?")},
                {furhat.ask("Do you want to make a Mad lib?")}
        )
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Yes>{
        random(
                {furhat.say("Great, let's start!")},
                {furhat.say("Awesome, let's go!")}
        )
        furhat.gesture(Gestures.Smile)
        furhat.say("I will ask you for word types, such as animals or colors.")
        furhat.say("Please answer with only one word")
        furhat.say("I will tell you if I didn't understand you.")
        delay(100)
        goto(FirstName)
    }

    onResponse<No>{
        furhat.gesture(Gestures.ExpressFear)
        random(
                {furhat.say("Okay, well that's a shame.")},
                {furhat.say("Oh, that's unfortunate. ")}
        )
        furhat.say("Have a nice day!")
        furhat.gesture(Gestures.Smile)
    }
}

val FirstName = state {
    onEntry {
        furhat.ask("Please give me a male first name")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<PersonName> {
        furhat.gesture(Gestures.Nod(duration=0.5), async = false)
        words.add(it.text)
        random(
            {furhat.say("${words[0]}, sounds like a nice guy. I'll add it to the story.")},
            {furhat.say("Alright, I will add ${words[0]} to the story.")}
        )
        goto(Profession)
    }
}

val Profession = state {
    onEntry {
        furhat.ask("Okay, then give me a profession.")
        furhat.gesture(Gestures.BrowRaise)
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
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Countries> {
        words.add(it.text)
        if (it.text == "The Netherlands" || it.text == "the Netherlands") {
            furhat.say{
                +Gestures.Oh
                +"Oh, ${words[2]}, I was made there!"}
        } else {
            random(
                    { furhat.say("${words[2]}, nice place.") },
                    { furhat.say("Alright, ${words[2]} seems to be a popular holiday destination.") }

            )
        }
        goto(ColourOne)
    }
}

val ColourOne = state {
    onEntry {
        furhat.ask("What is your favourite color?")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Color> {
        words.add(it.text)
        if (it.text == "Red" || it.text == "red") {
            furhat.say{
                +"${words[3]}, "
                +Gestures.Wink
                +"very romantic. "
                +"My internal circuitry prevents me from feeling love."}
        }
        else if (it.text == "Blue" || it.text == "blue") {
            furhat.say{
                +"Oh ${words[3]}, "
                +Gestures.ExpressFear
                +"like water."
                +"I don't like water, it damages my internal organs."}
        }
        else if (it.text == "Green" || it.text == "green") {
            furhat.say{
                +"${words[3]}, like the grass outside."
                +Gestures.BrowFrown
                +"I can't go outside, because i'm stuck inside this screen."}
        } else {
            furhat.say{
                +"${words[3]}, that's my favorite color as well!"
                +Gestures.Smile
                +"I will fill it in."}
        }
        goto(Superpower)
    }
}

val Superpower = state {
    onEntry {
        furhat.ask("What superpower would you like to have?")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Superpowers> {
        words.add(it.text)
        random(
                {
                    furhat.say {
                        +"${words[4]}, "
                        +Gestures.BrowFrown
                        +"a bit boring, but alright. Noted."
                    }
                },
                {
                    furhat.say {
                        +Gestures.BrowFrown
                        +"I wish I could do that, but I don't have a physical body. "
                        +"I will add ${words[4]} though."
                    }
                }
        )
        goto(Mammal)
    }
}

val Mammal = state {
    onEntry {
        furhat.ask("Can you name a mammal? For example, a tiger.")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Mammals> {
        words.add(it.text)
        if (it.text == "Cat" || it.text == "cat" || it.text == "cats") {
            furhat.say{
                +"${words[5]}, a good friend of mine, Mariët, "
                +Gestures.Smile
                +"also has a very cute cat."}
        } else {
            furhat.say({
                +"${words[5]}, those are awesome."
                +Gestures.Smile
                +"My favorite animal is a "
                random {
                    +"Wombat."
                    +"Platypus."
                    +"Naked Mole Rat."
                    +"Aardvark."
                }})
        }
        goto(ColourTwo)
    }
}

val ColourTwo = state {
    onEntry {
        furhat.ask("Please give me a number.")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Number> {
        words.add(it.text)
        random (
                {furhat.say("${words[6]}, that's the amount of transistors in my brain.")},
                {furhat.say("${words[6]}, I filled it in. I hope that is enough.")}
                )

        goto(FacialFeature)
    }
}

val FacialFeature = state {
    onEntry {
        furhat.ask("What about a facial feature?")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<FacialFeatures> {
        words.add(it.text)
        furhat.say{
            +"${words[7]}, "
            +Gestures.Wink
            +"some might say that is also my best feature. "}
            goto(Vegetable)
    }
}

val Vegetable = state {
    onEntry {
        furhat.ask("What vegetable do you dislike the most?")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Vegetables> {
        words.add(it.text)
        furhat.say{
            +"${words[8]}, "
            +Gestures.ExpressDisgust
            +" those are especially disgusting. I filled it in."}
            goto(Capital)
    }
}

val Capital = state {
    onEntry {
        furhat.ask("Name a European capital.")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Capitals> {
        words.add(it.text)
        if (it.text == "Enschede" || it.text == "enschede") {
            furhat.say{
                +"Oh, ${words[9]}. That's not a capital."
                +Gestures.Smile
                +"But I'll accept it, because my good friend Mariët lives there."}
        } else {
            furhat.say("${words[9]}, I heard it's quite beautiful there this time of year.")
        }
        goto(Weapon)
    }
}
val Weapon : State = state {
    onEntry {
        furhat.ask("And for the last category, name some kind of weapon.")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Weapon> {
        furhat.say("Come on! Can't you think of something more deadly?")
        goto(ProperWeapon)
    }
}
val ProperWeapon : State = state {
    onEntry {
        furhat.ask("Name a better weapon.")
    }

    onResponse<Weapon> {
        words.add(it.text)
        furhat.say("${words[10]}, that's better!")
        furhat.gesture(Gestures.Smile)
        goto(TellStory)
    }
}

val TellStory = state {
    onEntry {
        furhat.gesture(Gestures.Nod(duration=0.5), async = false)
        delay(100)
        furhat.say("Okay, we have filled in all the blanks.")
        furhat.gesture(Gestures.Smile(strength=0.5))
        delay(100)
        furhat.say("Let's hear the story!")
        delay(100)

        furhat.say{
            +"Meet our hero"
            +Gestures.Smile
            +"${words[0]}"}
        furhat.say("a super intelligent ${words[1]}.")
        delay(100)
        furhat.say{
            +"Due to an experiment gone wrong with the military of ${words[2]}, "
            +"${words[0]} became a ${words[3]} "
            +Gestures.Smile
            +"coloured superhero with amazing powers, like ${words[4]}."}
            delay(100)
            furhat.say("Every day, he battles bad guys with his pet ${words[5]}.")
            delay(100)
            furhat.say{
                +"Eventually it is discovered that our hero's arch nemesis, Doctor Evil, "
                + Gestures.Surprise
                + "distinguished by his ${words[6]} ${words[7]}s"}
            delay(100)
            furhat.say("has turned ${words[8]} into a weapon.")
            delay(100)
            furhat.say("Leading to a huge battle between the two in downtown ${words[9]}")
            delay(100)
            furhat.say{
                +"with slices of ${words[8]} "
                +Gestures.BrowRaise
                + "falling from the sky."}
            delay(100)
            furhat.say("Luckily, ${words[0]} is able to defeat him, capturing his enemy with his ${words[10]}.")
            delay(200)
            furhat.say("So, our hero saves the day yet again.")
            furhat.gesture(Gestures.Smile, async = false)
            goto(StoryEnd)
    }
}

val StoryEnd = state {
    onEntry {
        furhat.say("That was one crazy story!")
        delay(100)
        furhat.ask("Did you like the story?")
        furhat.gesture(Gestures.BrowRaise)
    }

    onResponse<Yes> {
        furhat.say("That's great!")
        furhat.gesture(Gestures.Smile)
        delay(200)
        goto(AnotherStory)
    }

    onResponse<No> {
        furhat.say("That's a shame!")
        furhat.gesture(ExpressFear)
        delay(200)
        goto(AnotherStory)
    }
}

val AnotherStory = state {
    onEntry {
        furhat.ask("Do you want to try again to make the story even better?")
    }
    onResponse<Yes> {
        furhat.say("That's great!")
        furhat.gesture(Gestures.Smile)
        furhat.say("I will ask you for words again.")
        words.clear()
        goto(FirstName)
    }

    onResponse<No> {
        random(
                { furhat.say("Okay, well that's a shame.") },
                { furhat.say("Oh thank God, it's so tiring to do this all day" ) }
        )
        furhat.gesture(ExpressFear)
        delay(200)
        furhat.say("Have a nice day!")
        furhat.gesture(Gestures.Smile)
    }
}
