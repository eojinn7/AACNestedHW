import java.lang.String;
import java.util.Scanner;
import javax.sound.sampled.Line;
import structures.AssociativeArray;
import structures.KeyNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class AACMappings {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  // topLevelCategory maps image paths to category names
  AACCategories topLevelCategory;

  // either topLevel or subcategory
  AACCategories currentCategory;

  // categories maps category names to ccdategories
  AssociativeArray<String, AACCategories> categories;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  public AACMappings(String filename) {
    PrintWriter pen = new PrintWriter(System.out, true);

    this.categories = new AssociativeArray<>();

    this.topLevelCategory = new AACCategories("Home Category");
    this.currentCategory = this.topLevelCategory;

    String[] imageTextPair = new String[2];

    try {
      File file = new File(filename);
      Scanner input = new Scanner(file);
      
      while (input.hasNextLine()) {
        String str = input.nextLine();

        imageTextPair = str.split(" ");
        
        String imageLoc = imageTextPair[0];
        String text = imageTextPair[1];

        pen.println("+++++++++++++++++++++++++");


        if (str.charAt(0) != '>') {

          if (!this.categories.hasKey(imageLoc)) {
            this.categories.set(imageLoc, new AACCategories(text));
          }

          this.topLevelCategory.addItem(imageLoc, text);

          


          try {
            this.currentCategory = this.categories.get(imageLoc);
          } catch (KeyNotFoundException e) {
            e.printStackTrace();
          }
        }
        else {
          imageLoc = imageLoc.substring(1, imageLoc.length());
          this.currentCategory.addItem(imageLoc, text);
        } // if
  
      } // while

      input.close();
      this.currentCategory = this.topLevelCategory;
    } catch (FileNotFoundException e) {
      System.err.println("Error: File not found exception");
    }




  }

  // +---------+------------------------------------------------
  // | Methods |
  // +---------+

  public void add(String imageLoc, String text) {
    this.currentCategory.addItem(imageLoc, text);
  } // addItem(String, String)

  public void reset() {
    this.currentCategory = topLevelCategory;
  } // reset()

  public String getCurrentCategory() {
    return this.currentCategory.getCategory();
  } // getCurrentCategory()

  public String getText(String imageLoc) throws KeyNotFoundException {
    return this.currentCategory.getText(imageLoc);
  } // getText(String)

  public String[] getImageLocs() {
    return this.currentCategory.getImages();
  } // getImageLocs



  public boolean isCategory(String imageLoc) {
    try {
      String txt = this.currentCategory.getText(imageLoc);
      return true;
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
      return false;
    }
  } // isCategory(String)

  
  public void writeToFile(String filename) {
    PrintWriter pen = new PrintWriter(System.out, true);

    for (String category : topLevelCategory.getImages()) {
      pen.println(category);
    }
  } // writeToFile(String)






}
