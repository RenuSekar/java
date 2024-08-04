import java.util.Random;

public class Sentence{

public static void main(String[] args){

String[] articles = {"the","a","one","some","any"};
String[] noun = {"boy","girl","dog","town","car"};
String[] verbs = {"drove","jumped","ran","walked","skipped"};
String[] preposition = {"to","from","over","under","on"};

Random random = new Random();

//to generate 20 sentences
for(int i = 0;i<20;i++){
String sentence = generateSentence(random,articles,noun,verbs,preposition);
System.out.println(capitalize(sentence) + ".");
}
}

private static String generateSentence(Random random,String[] articles,String[] noun,String[] verbs,String[] preposition){
StringBuilder sentence = new StringBuilder();

//construct the sentence 
sentence.append(articles[random.nextInt(articles.length)]).append(" ");
sentence.append(noun[random.nextInt(noun.length)]).append(" ");
sentence.append(verbs[random.nextInt(verbs.length)]).append(" ");
sentence.append(preposition[random.nextInt(preposition.length)]).append(" ");
sentence.append(articles[random.nextInt(articles.length)]).append(" ");
sentence.append(noun[random.nextInt(noun.length)]).append(" ");

return sentence.toString();
}

private static String capitalize(String sentence){
return Character.toUpperCase(sentence.charAt(0))+sentence.substring(1);
}
}