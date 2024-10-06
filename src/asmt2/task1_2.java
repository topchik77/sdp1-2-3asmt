package asmt2;
//Factory pattern is ideal for adding new document types without changing
// existing code.
interface Document {
}

class PDFDocument implements Document {
    public PDFDocument() {
        System.out.println("PDF Document created.");
    }
}

class WordDocument implements Document {
    public WordDocument() {
        System.out.println("Word Document created.");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
}

class PDFDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new PDFDocument();
    }
}

class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

public class task1_2 {
    public static void main(String[] args) {
        DocumentFactory pdfFactory = new PDFDocumentFactory();
        pdfFactory.createDocument();

        DocumentFactory wordFactory = new WordDocumentFactory();
        wordFactory.createDocument();
    }
}

