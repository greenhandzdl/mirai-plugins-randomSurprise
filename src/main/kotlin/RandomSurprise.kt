package com.greenhandzdl.randomSurprise

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.contact.isBotMuted
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.message.data.messageChainOf
import net.mamoe.mirai.utils.info
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

object RandomSurprise : KotlinPlugin(
    JvmPluginDescription(
        id = "com.greenhandzdl.randomSurprise",
        name = "randomSurprise",
        version = "0.0.1",
    ) {
        author("greenhandzdl")
    }
) {
    override fun onEnable() {
        logger.info { "randomSurprise loaded" }

        //初始配置文件
        val data = File("$dataFolder/random.json")
        val djson = JSONObject()
        if (!data.exists()){
            data.createNewFile()
            djson.put("max",0)
            djson.put("0","获得了豁免权")//默认配置
            File("$dataFolder/random.json").writeText(djson.toString())
        }

        /**
        val permission = File("$configFolder/permission.json")
        val pjson =JSONObject(permission)
        if (!permission.exists()){
            permission.createNewFile()
            pjson.put("admin0","123456789")
            pjson.put("num","0")//从零开始！！
            permission.writeText(pjson.toString())
        }
        */

        globalEventChannel().subscribeAlways<GroupMessageEvent> {
            if(!group.isBotMuted) when{
                message.contentToString() == "r" ->{
                    val djson = JSONObject(data.readText())
                    val max = djson.getInt("max")
                    group.sendMessage(messageChainOf(At(sender) + PlainText("幸运大转盘出来了哦~ \n") + PlainText("${djson.getString("${Random.nextInt(0,max+1)}")}")))
                }
                /**
                 * 需要权限的操作（不想写了，下个版本再见)
                 * message.contentToString().startsWith("r perm add")
                 * message.contentToString().startsWith("r perm del")
                 * message.contentToString().startsWith("r perm ls")
                 * message.contentToString().startsWith("r word add")
                 * message.contentToString().startsWith("r perm del")
                 * message.contentToString().startsWith("r perm ls")
                 */
            }
        }
    }

    override fun onDisable() {
        super.onDisable()
        logger.info{"randomSurprise Disabled"}
    }
}