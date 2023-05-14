
// Imported Libraries
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BinaryTree {
    Node root;

    BinaryTree() {
        root = null; // inicialização da raíz
    }

    boolean search(Character key) {
        return search(key, root);
    }

    boolean search(Character key, Node i) {
        boolean answer;

        if (i == null)
            answer = false; // se estiver null, o elemento não está contido na árvore
        else if (key.getName().compareTo(i.element.getName()) == 0)
            answer = true; // a chave de pesquisa foi encontrada
        else if (key.getName().compareTo(i.element.getName()) < 0)
            answer = search(key, i.left); // chamada recursiva passando o nó filho pelo lado esquerdo
        else
            answer = search(key, i.right); // chamada recursiva passando o nó filho pelo lado direito

        return answer;
    }

    Character getSmallest() {
        Character answer = null;

        if (root != null) {
            Node i;

            for (i = root; i.left != null; i = i.left)
                ;

            answer = i.element;
        }

        return answer;
    }

    Character getLargest() {
        Character answer = null;

        if (root != null) {
            Node i;

            for (i = root; i.right != null; i = i.right)
                ;

            answer = i.element;
        }

        return answer;
    }

    Node getLargestLeft(Node i, Node j) {
        if (j.right == null) {
            i.element = j.element;
            j = j.left;
        } else
            j.right = getLargestLeft(i, j.right);

        return j;
    }

    void addWithReference(Character in) throws Exception {
        root = add(in, root);
    }

    Node add(Character in, Node i) throws Exception {
        if (i == null) // verifica se o nó atual é uma folha nula ou se a árvore está vazia
            i = new Node(in); // Cria um novo nó com o item recebido
        else if (in.getName().compareTo(i.element.getName()) < 0) // verifica se o item recebido é menor que o nó atual
            i.left = add(in, i.left); // chamada recursiva passando o nó filho pela esquerda
        else if (in.getName().compareTo(i.element.getName()) > 0) // verifica se o item recebido é maior que o nó atual
            i.right = add(in, i.right); // chamada recursiva passando o nó filho pela direita
        else
            throw new Exception("Erro ao adicionar item, não são permitidos itens repetidos");

        return i; // Retorna o item adicionado
    }

    void addByFather(Character in) throws Exception {
        if (root == null) // verifica se o nó atual é uma folha nula ou se a árvore está vazia
            root = new Node(in); // Cria um novo nó com o item recebido
        else if (in.getName().compareTo(root.element.getName()) < 0) // verifica se o item recebido é menor que o nó
                                                                     // atual
            addByFather(in, root.left, root); // chama o método de sobrecarga para fazer uma busca recursiva na
                                              // subárvore esquerda
        else if (in.getName().compareTo(root.element.getName()) > 0) // verifica se o item recebido é maior que o nó
                                                                     // atual
            addByFather(in, root.right, root); // chama o método de sobrecarga para fazer uma busca recursiva na
                                               // subárvore direita
        else
            throw new Exception("Erro ao adicionar item, não são permitidos itens repetidos");
    }

    void addByFather(Character in, Node i, Node father) throws Exception {
        if (i == null) { // verifica se o nó atual é uma folha nula
            if (in.getName().compareTo(father.element.getName()) < 0) // verifica se o item recebido é menor que o nó
                                                                      // pai
                father.left = new Node(in); // o filho deve ser adicionado a esquerda do pai
            else // item recebido é maior que o nó pai
                father.right = new Node(in); // o filho deve ser adicionado a direita do pai
        } else if (in.getName().compareTo(i.element.getName()) < 0) // verifica se o item recebido é menor que o nó
                                                                    // atual
            addByFather(in, i.left, i); // chamada recursiva passando a esquerda do nó atual como filho e o nó atual
                                        // como pai
        else if (in.getName().compareTo(i.element.getName()) > 0) // verifica se o item recebido é maior que o nó atual
            addByFather(in, i.right, i); // chamada recursiva passando a direita do nó atual como filho e o nó atual
                                         // como pai
        else
            throw new Exception("Erro ao adicionar item, não são permitidos itens repetidos");
    }

    void remove(Character in) throws Exception {
        root = remove(in, root);
    }

    Node remove(Character in, Node i) throws Exception {
        if (i == null)
            throw new Exception("Erro ao remover elemento, a árvore binária está vazia");

        if (in.getName().compareTo(i.element.getName()) < 0)
            i.left = remove(in, i.left);
        else if (in.getName().compareTo(i.element.getName()) > 0)
            i.right = remove(in, i.right);
        else if (i.right == null)
            i = i.left;
        else if (i.left == null)
            i = i.right;
        else
            i.left = getLargestLeft(i, i.left);

        return i;
    }

    void preOrderTraversal(Node i) {
        if (i != null) {
            i.element.print();
            preOrderTraversal(i.left);
            preOrderTraversal(i.right);
        }
    }

    void inOrderTraversal(Node i) {
        if (i != null) {
            inOrderTraversal(i.left);
            i.element.print();
            inOrderTraversal(i.right);
        }
    }

    void postOrderTraversal(Node i) {
        if (i != null) {
            postOrderTraversal(i.left);
            postOrderTraversal(i.right);
            i.element.print();
        }
    }
}

class Node {
    Character element;
    Node left, right;

    Node(Character in) {
        this(in, null, null);
    }

    Node(Character in, Node left, Node right) {
        this.element = in;
        this.left = left;
        this.right = right;
    }
}

// Character class
class Character {

    // Declaration of attributes
    private String name, dateOfBirth, gender, homeworld;
    private ArrayList<String> hairColor, skinColor, eyesColor;
    private int height;
    private double mass;
    private long numComp, numMov;

    // Constructors
    public Character() {
        this.name = this.dateOfBirth = this.gender = this.homeworld = null;
        this.hairColor = new ArrayList<String>();
        this.skinColor = new ArrayList<String>();
        this.eyesColor = new ArrayList<String>();
        this.height = -1;
        this.mass = -1;
    }

    public Character(String name, int height, double mass, ArrayList<String> hairColor, ArrayList<String> skinColor,
            ArrayList<String> eyesColor, String dateOfBirth, String gender, String homeworld) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyesColor = eyesColor;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.homeworld = homeworld;
    }

    /**
     * Clones an object
     * 
     * @return - returns a cloned object
     */
    public Character cloneCharacter() {
        Character clone = new Character();

        clone.name = this.name;
        clone.height = this.height;
        clone.mass = this.mass;
        clone.hairColor = this.hairColor;
        clone.skinColor = this.skinColor;
        clone.eyesColor = this.eyesColor;
        clone.dateOfBirth = this.dateOfBirth;
        clone.gender = this.gender;
        clone.homeworld = this.homeworld;

        return clone;
    }

    // Getters through encapsulation
    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public double getmass() {
        return mass;
    }

    public ArrayList<String> getHairColor() {
        return hairColor;
    }

    public ArrayList<String> getSkinColor() {
        return skinColor;
    }

    public ArrayList<String> getEyesColor() {
        return eyesColor;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getgender() {
        return gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public long getNumComp() {
        return numComp;
    }

    public long getNumMov() {
        return numMov;
    }

    // Setters through encapsulation
    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setmass(double mass) {
        this.mass = mass;
    }

    public void setHairColor(ArrayList<String> hairColor) {
        this.hairColor = hairColor;
    }

    public void setSkinColor(ArrayList<String> skinColor) {
        this.skinColor = skinColor;
    }

    public void setEyesColor(ArrayList<String> eyesColor) {
        this.eyesColor = eyesColor;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setgender(String gender) {
        this.gender = gender;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    /**
     * Prints each attribute of an object of the character class
     */
    public void print() {
        String hair = Arrays.toString(this.hairColor.toArray()).replace("[", "").replace("]", "");
        String skin = Arrays.toString(this.skinColor.toArray()).replace("[", "").replace("]", "");
        String eyes = Arrays.toString(this.eyesColor.toArray()).replace("[", "").replace("]", "");
        String aux = Double.toString(this.mass), cut = "";

        System.out.print(" ## " + this.name + " ## " + this.height + " ## ");

        // treating decimals for double
        for (int index = 0, i = 0; index < aux.length(); index++) {
            if (aux.charAt(index) == '.' && aux.charAt(index + 1) == '0') {
                while (i <= aux.length() - 3) {
                    cut += aux.charAt(i);
                    i++;
                }
                System.out.print(cut);
                break;
            } else if (aux.charAt(index) == '.'
                    && (isNumber(aux.charAt(index + 1)) && aux.charAt(index + 1) != 0)) {
                System.out.print(aux);
            }
        }

        System.out.println(" ## " + hair + " ## " + skin + " ## " + eyes + " ## " + this.dateOfBirth + " ## "
                + this.gender + " ## " + this.homeworld + " ## ");
    }

    /**
     * Reads a text file and then assigns the identified values to the attributes of
     * the Character class
     * 
     * @param file - the document to be read
     * @throws IOException - IO error handling
     */
    public void read(String file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));

        String line, aux = "", tempString = "";

        // store the file contents to a string
        while ((line = br.readLine()) != null) {
            aux = line;
        }
        br.close();

        int index = 0, auxQuoteMark = 0;

        // find - character name -
        while (true) {
            index++;

            if (aux.charAt(index) == '\'')
                auxQuoteMark++;

            if (auxQuoteMark == 3) {
                index++;

                while (aux.charAt(index) != '\'') {
                    tempString += aux.charAt(index);
                    index++;
                }
                this.name = tempString;
                index += 2;
                break;
            }
        }
        auxQuoteMark = 0;
        tempString = "";

        // find - character heigth -
        while (true) {
            index++;

            if (aux.charAt(index) == '\'')
                auxQuoteMark++;

            if (auxQuoteMark == 3) {
                index++;

                // if the height is unknown, set 0
                if (aux.charAt(index) == 'u') {
                    this.height = 0;

                    while (aux.charAt(index) != '\'')
                        index++;

                    index += 2;
                    break;
                } else {
                    while (aux.charAt(index) != '\'' && isNumber(aux.charAt(index))) {
                        tempString += aux.charAt(index);
                        index++;
                    }
                    this.height = Integer.parseInt(tempString);
                    index += 2;
                    break;
                }
            }
        }
        auxQuoteMark = 0;
        tempString = "";

        // find - character mass -
        while (true) {
            index++;

            if (aux.charAt(index) == '\'')
                auxQuoteMark++;

            if (auxQuoteMark == 3) {
                index++;

                // if the mass is unknown, set 0
                if (aux.charAt(index) == 'u') {
                    this.mass = 0;

                    while (aux.charAt(index) != '\'')
                        index++;

                    index += 2;
                    break;
                } else {
                    while (aux.charAt(index) != '\''
                            && (isNumber(aux.charAt(index)) || aux.charAt(index) == '.'
                                    || aux.charAt(index) == ',')) {

                        // if it's a comma, replace it with a blank space
                        if (aux.charAt(index) == ',') {
                            tempString += "";
                            index++;
                        }

                        tempString += aux.charAt(index);
                        index++;
                    }
                    this.mass = Double.parseDouble(tempString);
                    index += 2;
                    break;
                }
            }
        }
        auxQuoteMark = 0;
        tempString = "";

        // find - character hair color -
        while (true) {
            index++;

            if (aux.charAt(index) == '\'')
                auxQuoteMark++;

            if (auxQuoteMark == 4) {
                index += 2;
                break;
            }

            if (auxQuoteMark == 3) {
                index++;

                while (aux.charAt(index) != '\'') {
                    if (aux.charAt(index) == ',') {
                        index++;
                        break;
                    }
                    tempString += aux.charAt(index);
                    index++;
                }
                index--; // decrementing by 1 to ensure that it doesn't loop infinitely
                this.hairColor.add(tempString);
                tempString = "";
            }
        }
        auxQuoteMark = 0;

        // find - character skin color -
        while (true) {
            index++;

            if (aux.charAt(index) == '\'')
                auxQuoteMark++;

            if (auxQuoteMark == 4) {
                index += 2;
                break;
            }

            if (auxQuoteMark == 3) {
                index++;

                while (aux.charAt(index) != '\'') {
                    if (aux.charAt(index) == ',') {
                        index++;
                        break;
                    }
                    tempString += aux.charAt(index);
                    index++;
                }
                index--; // decrementing by 1 to ensure that it doesn't loop infinitely
                this.skinColor.add(tempString);
                tempString = "";
            }
        }
        auxQuoteMark = 0;

        // find - character eyes color -
        while (true) {
            index++;

            if (aux.charAt(index) == '\'')
                auxQuoteMark++;

            if (auxQuoteMark == 4) {
                index += 2;
                break;
            }

            if (auxQuoteMark == 3) {
                index++;

                while (aux.charAt(index) != '\'') {
                    if (aux.charAt(index) == ',') {
                        index++;
                        break;
                    }
                    tempString += aux.charAt(index);
                    index++;
                }
                index--; // decrementing by 1 to ensure that it doesn't loop infinitely
                this.eyesColor.add(tempString);
                tempString = "";
            }
        }
        auxQuoteMark = 0;

        // find - character date of birth -
        while (true) {
            index++;

            if (aux.charAt(index) == '\'')
                auxQuoteMark++;

            if (auxQuoteMark == 3) {
                index++;

                while (aux.charAt(index) != '\'') {
                    tempString += aux.charAt(index);
                    index++;
                }
                this.dateOfBirth = tempString;
                index += 2;
                break;
            }
        }
        auxQuoteMark = 0;
        tempString = "";

        // find - character gender -
        while (true) {
            index++;

            if (aux.charAt(index) == '\'')
                auxQuoteMark++;

            if (auxQuoteMark == 3) {
                index++;

                while (aux.charAt(index) != '\'') {
                    tempString += aux.charAt(index);
                    index++;
                }
                this.gender = tempString;
                index += 2;
                break;
            }
        }
        auxQuoteMark = 0;
        tempString = "";

        // find - character homeworld -
        while (true) {
            index++;

            if (aux.charAt(index) == '\'')
                auxQuoteMark++;

            if (auxQuoteMark == 3) {
                index++;

                while (aux.charAt(index) != '\'') {
                    tempString += aux.charAt(index);
                    index++;
                }
                this.homeworld = tempString;
                index += 2;
                break;
            }
        }
        auxQuoteMark = 0;
        tempString = "";

    }

    /**
     * Takes a character and says whether it is a number or not
     * 
     * @param in - character
     * @return - boolean value 'true' if it is a number
     */
    private static boolean isNumber(char in) {
        return in >= 48 && in <= 57;
    }

}
