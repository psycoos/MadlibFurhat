package furhatos.app.newskill.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.newskill.nlu.*

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
        furhat.say("${it.text}, I will add it to the story.")
        reentry()
    }
}
