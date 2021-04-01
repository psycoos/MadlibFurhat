package furhatos.app.newskill.nlu

import furhatos.nlu.*
import furhatos.nlu.grammar.Grammar
import furhatos.nlu.kotlin.grammar
import furhatos.nlu.common.Number
import furhatos.util.Language



class FirstName : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Jack")
    }
}

class Professions : Intent()

class Countries : Intent()

class Colour : Intent()

class Superpowers : Intent()

class Mammals: Intent()

class FacialFeatures : Intent()

class Vegetables : Intent()

class Capitals : Intent()

class Weapon : Intent()