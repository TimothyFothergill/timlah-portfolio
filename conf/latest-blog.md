# Classes vs Structs in Unity C# Development

Recently I've found myself playing around with a few ideas for [Skell's Quest](https://www.timlah.com/projects). I'm 
currently in the middle of the third big update for it, where I introduce a bunch of new features, such as:

- New NPC Types
- New Quest Types
- All Stats implemented
- Attributes
- Abilities for each stat
- Equipment
- A character loadout screen
- Banking
- and much more...

This has been exciting, however it's a long process. I'm excited to say it should be available in May.

One of the most exciting parts of this update has been messing with libraries like Linq more and getting used to the 
difference between using classes and structs. As a game developer, I want to explore different avenues to getting my 
game across the finish line.

## Classes
```
public class PlayerStat
{
    string  statName;
    string  statDescription;
    int     currentLevel;
    int     maxLevel;
    int     currentXP           = 0;
    int     maxXP               = 100;
    bool    isCombatSkill       = false;
    Sprite  sprite;
    string  spritePath;

    public PlayerStat(string n, string d, bool t = false) {
        statName = n; statDescription = d; currentLevel = 1; maxLevel = 99;
        isCombatSkill = t;
        sprite = Resources.Load<Sprite>($"{n}");
    }
    // etc...
```
This is the usual one that people tend to use. The above code snippet is a small example of what a class is. It's a bit
of logic which you can use to describe something. You can give it methods to manage the values of that class as well.

For instance, you can write a method which will update the above PlayerStat's currentXP. This is supported by Unity out
of the box, can be viewed in the inspector and interacted with in a variety of ways.

But what if you want to restrict access somewhat? Introducing the simple struct:

## Struct

```
[Serializable]
public struct SerializedQuestStatLevels {
    public int levelRequired;
    public PlayerStat stat;
}
```

The [Serializable] property allows Unity to automatically transform the data structure into a Unity readable format. 
This makes the property available in the inspector (so long as you also mark it as public, although you can view 
private properties with the Debug option set in the inspector, but I digress).

Notice how much simpler this is? A stuct is really useful when you do not want the values to change. You want to set the
struct up, then use it simply as a reference to something.

## When do I use Struct over Class?

I like to use structs now wherever possible. See, Skell's Quest is becoming quite data heavy. So often I find myself not
needing to edit some data set up. An example where structs are useful for me - Quests. In the above snippet, you
might be able to gather than it is a simple little bit of data that holds two values: A PlayerStat and a level 
requirement. If I need to edit even a small class in future by way of code logic, I use a class.

Thanks so much for joining me for another blog post. One of the things I've recently made is a script that converts a 
csv file into ScriptableObject's. This is a tool that sits in my Unity editor and with the click of a button I can 
generate all the data without impacting the references in the game. Maybe this'll be the topic of another blog post.

Until next time, thanks for reading, much love to all and happy coding. - Timlah