
// Imported Libraries
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class LinkedStack {
    Cell top;

    LinkedStack() {
        top = null;
    }

    boolean isEmpty() {
        return top == null;
    }

    Character peek() throws Exception {
        if (isEmpty())
            throw new Exception("Erro ao acessar elemento, a pilha está vazia!");

        return top.element;
    }

    int size() {
        int counter = 0;
        for (Cell i = top; i != null; i = i.next)
            counter++;

        return counter;
    }

    void push(Character in) {
        Cell temp = new Cell(in); // Cria uma nova célula com o elemento recebido
        temp.next = top; // Indica que a nova célula deve apontar para o topo
        top = temp; // Atualiza top para apontar para a nova célula, tornando-a o novo topo
    }

    Character pop() throws Exception {
        if (isEmpty())
            throw new Exception("Erro ao remover elemento, a pilha ligada está vazia!");

        Character toBeRemoved = top.element; // Character a ser removido e retornado
        Cell temp = top; // Cria uma nova célula que aponta para o mesmo objeto que o topo
        top = top.next; // Atualiza top para apontar para a célula abaixo, removendo o topo atual
        temp.next = null; // Desvincula o objeto que representa o elemento removido da pilha

        return toBeRemoved;
    }

    void printLinkedStack() {
        for (Cell i = top; i != null; i = i.next)
            i.element.print();
    }
}

class Cell {
    Character element;
    Cell next;

    Cell() {
        this.element = null;
        this.next = null;
    }

    Cell(Character in) {
        this.element = in;
        this.next = null;
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