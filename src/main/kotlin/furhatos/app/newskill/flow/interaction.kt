package furhatos.app.newskill.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.newskill.nlu.*

val words: MutableList<String> = ArrayList()

val Start : State = state(Interaction) {

    onEntry {
        furhat.ask("Hi there. Would you like to create a story together?")
    }

    onResponse<Yes>{
        furhat.say("Alright. Let's stop.")
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
