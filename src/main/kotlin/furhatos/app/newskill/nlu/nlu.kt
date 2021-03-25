package furhatos.app.newskill.nlu

import furhatos.nlu.*
import furhatos.nlu.grammar.Grammar
import furhatos.nlu.kotlin.grammar
import furhatos.nlu.common.Number
import furhatos.util.Language

class Animal : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("dog", "cat", "horse", "ant")
    }
}

class City : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("amsterdam", "london", "paris", "berlin")
    }
}
//
//class WhoEntity: WildcardEntity("who", SendMessageIntent())
//class MessageEntity: WildcardEntity("message", SendMessageIntent())
//
//class SendMessageIntent(): Intent() {
//
//    var who: WhoEntity? = null
//    var message: MessageEntity? = null
//
//    override fun getExamples(lang: Language): List<String> {
//        return listOf(
//                "Tell @who to @message",
//                "Send @who a message saying @message"
//        );
//    }
//
//}

