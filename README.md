# KeyCombatlog
Seyali's Combatlogger


![alt-text](https://cdn.discordapp.com/attachments/302901414269681664/751701900272992296/title.png)
![alt-text](https://cdn.discordapp.com/attachments/302901414269681664/751701894526926868/features.png)

(Tab Completion)

![alt-text](https://cdn.discordapp.com/attachments/302901414269681664/751701893260247050/Annotation_2020-09-05_020036.png)

(Default messages for combat logging.)

![alt-text](https://cdn.discordapp.com/attachments/302901414269681664/751702453853880330/Annotation_2020-09-05_021659.png)























Example config.yml:

############################################################
# +------------------------------------------------------+ #
# |                    Main Settings                     | #
# +------------------------------------------------------+ #
############################################################


#Plugin prefix

prefix: "&6Combat Log &r&7» "


#Being attacked by mobs also triggers combatlog.

mobs-trigger-combatlog: true


#Disable flight upon taking damage.

disable-flight: true


#Kill player if they combat log.

kill-on-disconnect: true


#Time (in minutes) until the player can safely disconnect.

timer: 5



############################################################
# +------------------------------------------------------+ #
# |                   LANGUAGE FILE                      | #
# +------------------------------------------------------+ #
############################################################


#Message sent to the aggressor.

attacker-logged: "You have entered combat! Do not logout!"


#Message sent to the person that was attacked.

defender-logged: "You have entered combat! Do not logout!"


#Message sent when you can log out.

safe-to-leave: "You are no longer in combat! It is safe to log out!"


#Message sent for correct command usage.

command-usage: "&7Incorrect parameters! Usage: /cl <check/on/off>!"


#Message sent when /cl check returns TRUE.

is-combat-logged: "&cYou are currently combat logged!"


#Message sent when /cl check returns FALSE.

is-not-combat-logged: "&7You are not combat logged!"

