class LinkedList {
  private Node[] table;
  private int size;
  int pos;

  public LinkedList(int tableSize) {
    table = new Node[tableSize];
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isproductempty(int pos) {
    Node start = table[pos];
    if (start == null) {
      return false;
    } else {
      return true;
    }
  }

  public void makeEmpty() {
    int l = table.length;
    table = new Node[l];
    size = 0;
  }

  public int getSize() {
    return size;
  }

  public int getproductsize(int pos) {
    Node start = table[pos];
    if (start == null) {
      return 0;
    } else {
      int s = 0;
      while (start != null) {
        s++;
        start = start.next;
      }
      return s;
    }
  }

  public String insert(int val, String pname) {
    if (size >= 50) {
      return "\n     -   Unable to register new item, Warehouse is full.";
    }
    size++;
    pos = val;
    while (pos >= 10) {
      pos /= 10;
    }
    Node nptr = new Node(val, pname);
    if (table[pos] == null) {
      table[pos] = nptr;
    } else {
      nptr.next = table[pos];
      table[pos] = nptr;
    }
    return ("\n     -   Item Recorded Succesfully. \n\n\t Product assigned ID : " + val);
  }

  public String remove(int val) {
    pos = val;
    while (pos >= 10) {
      pos /= 10;
    }
    Node start = table[pos];
    Node end = start;
    if (start.data == val) {
      size--;
      table[pos] = start.next;
      return "\n     -   Item Dispatched Succesfully.";
    }
    while (end.next != null && end.next.data != val) {
      end = end.next;
    }
    if (end.next == null) {
      return "\n     -   Element Not Found in the Warehouse";
    }
    size--;
    end.next = end.next.next;
    table[pos] = start;
    return "\n     -   Item Dispatched Succesfully.";
  }

  public String search(int val) {
    pos = val;
    String str, id;
    id = Integer.toString(val);
    while (pos >= 10) {
      pos /= 10;
    }
    Node ptr = table[pos];
    if (pos == 1) {
      str = "Automotive";
    } else if (pos == 2) {
      str = "Electronic";
    } else if (pos == 3) {
      str = "Fabric";
    } else if (pos == 4) {
      str = "Food";
    } else {
      str = "Other";
    }
    while (ptr != null) {
      if (ptr.data == val) {
        return ("\n     -   Item present in Warehouse.\n\n\t Product Name : " + ptr.name + "\n\t Product Category : " + str + "\n\t Product Price : " + id.substring(1, id.length()));
      }
      ptr = ptr.next;
    }
    return "\n     -   Item Not Present in Warehouse.";
  }

  public String print(int val) {
    if (isproductempty(val) != false) {
      Node start = table[val];
      String str = ("\n\tProduct Present in Warehouse Field No. " + val + ", Contains " + getproductsize(val) + " Items." + "\n\tSr.No." + "\tItem ID" + "\tName");
      for (int i = 1; start != null; i++) {
        str = (str + "\n\t " + i + "\t" + start.data + "\t" + start.name);
        start = start.next;
      }
      return str;
    } else {
      return "\n\t Warehouse Field is Not Containing Any Items.";
    }
  }
}