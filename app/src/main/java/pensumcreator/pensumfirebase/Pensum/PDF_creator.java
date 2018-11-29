package pensumcreator.pensumfirebase.Pensum;

import android.app.Activity;
import android.graphics.pdf.PdfDocument;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDF_creator {

    /*
     * This may or may not work ..
     */
    public File generateDocument(Activity activity, String fileName, ViewGroup container) throws IOException {
        File f = new File(activity.getExternalFilesDir(null), fileName);
        PdfDocument document = new PdfDocument();
        try{
            for(int i=0;i<container.getChildCount();i++){
                View v = container.getChildAt(i);
                PdfDocument.PageInfo.Builder pageBuilder = new PdfDocument.PageInfo.Builder(v.getWidth(), v.getHeight(), i);
                PdfDocument.Page page = document.startPage(pageBuilder.create());
                v.draw(page.getCanvas());
                document.finishPage(page);
            }

            document.writeTo(new FileOutputStream(f));
        } finally{
            if(document!=null){
                document.close();
            }
        }
        return f;
    }


}
