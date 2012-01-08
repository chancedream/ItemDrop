
public class SkillParser extends Parser {
    private int iName, iId, iCharClass;

    public SkillParser(String[] title) {
        super(title);
        iName = index("skill");
        iId = index("Id");
        iCharClass = index("charclass");
    }
    
    public Skill parse(String[] tokens) {
        return new Skill(tokens[iName], parseInt(tokens[iId]), tokens[iCharClass]);
    }

}
