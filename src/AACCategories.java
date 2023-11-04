import java.lang.String;
import structures.AssociativeArray;
import structures.KeyNotFoundException;

/**
 * AACCategories works with representing a single category of items 
 * in the AAC, and stores the mapping between the image location and 
 * the name of the category (text).
 *
 * @author Jinny Eo
 */
public class AACCategories {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  String name;
  AssociativeArray<String, String> arr = new AssociativeArray<>();

  int size;
  
  // +--------------+------------------------------------------------
  // | Constructors |arr.keys()
  // +------------ // currentCategory maps -+

  public AACCategories(String name) {
    this.name = name;
    this.arr = new AssociativeArray<String, String>();
    this.size = 0;
  } // AACCategories(name)

  // +---------+------------------------------------------------
  // | Methods |
  // +---------+

  public void addItem(String imageLoc, String text) {
    this.arr.set(imageLoc, text);
  } // addItem(String, String)

  public String getCategory() {
    return this.name;
  } // getCategory()


  public String[] getImages() {

    String[] images = new String[this.arr.size()];
    int i = 0;

    for (Object key : arr.keys()) {
      images[i] = (String) key;
      i++;
    }

    // for (KVPair<String,String> pair : arr.getPairs()) {
    //   if (pair.key != null) {
    //     images[i] = pair.key;
    //   }
    //   i++;
    // }

    return images;
  } // getImages()


  public String getText(String imageLoc) throws KeyNotFoundException {
    return this.arr.get(imageLoc);
  } // getText(String)

  public boolean hasImage(String imageLoc) {
    return this.arr.hasKey(imageLoc);
  } // hasImage(String)

}
