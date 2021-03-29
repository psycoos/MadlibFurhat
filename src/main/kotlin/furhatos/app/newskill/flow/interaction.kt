package furhatos.app.newskill.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.newskill.nlu.*

import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.*


val words: MutableList<String> = ArrayList()


val Start : State = state(Interaction) {

    onEntry {
        random(
            {   furhat.say("Hello") },
            {   furhat.say("Oh, hello welcome") },
            {   furhat.say("Hi") }
        )
        furhat.say("I am Furhat.")
        furhat.gesture(Gestures.BigSmile(duration=2.0))
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
        furhat.gesture(Gestures.Smile, async = false)


        furhat.say("A Madlib works as follows. I will ask you to come up with certain types of words. " +
                "Then I will use them to form a story.")
    }

    onResponse<No>{
        random(
            {furhat.say("Okay, well that's a shame. Have a nice day!")},
            {furhat.say("Oh, that's unfortunate. Have a nice day!")}
        )
        furhat.gesture(Gestures.ExpressSad, false)
    }

}


val Start : State = state(Interaction) {

    onEntry {
        furhat.ask("Hi there. Would you like to create a story together?")
    }

    onResponse<Yes>{
        furhat.say("Alright. Let's begin.")
        goto(Story)
    }

    onResponse<No>{
        furhat.say("That's sad.")
    }
}

val Story = state {
    onEntry {
        furhat.ask("Please give me an animal")
    }

    onResponse<Animal> {
        words.add(it.text)
        furhat.say("${words[0]}, I will add it to the story.")
        goto(Word2)
    }
}

val Word2 = state {
    onEntry {
        furhat.ask("What is your favourite city?")
    }

    onResponse<City> {
        words.add(it.text)
        furhat.say("${words[1]}, I will add it to the story.")
        goto(TellStory)
    }
}

val TellStory = state {
    onEntry {
        furhat.say("I have adopted a ${words[0]} from the zoo in ${words[1]}")
    }
}
