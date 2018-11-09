package com.example.jonathanlarsen.pensumfirebase.Pensum;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jonathanlarsen.pensumfirebase.R;
import com.example.jonathanlarsen.pensumfirebase.Storage_DataModels.InternalStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static com.example.jonathanlarsen.pensumfirebase.MainActivity.PENSUM_LIST_OBJECT_KEY;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class SendPensum_Fragment extends Fragment implements View.OnClickListener {

    private final int PAGE_WIDTH = 612;
    private final int PAGE_HEIGHT = 792;

    /**
     * For identifying current view mode read/create/listing/options
     *
     * @author androidsrc.net
     */
    interface CurrentView {
        int OPTIONS_LAYOUT = 1;
        int READ_LAYOUT = 2;
        int WRITE_LAYOUT = 3;
        int PDF_SELECTION_LAYOUT = 4;
    }

    /**
     * FrameLayout child views. We will manage our UI to one layout Hide/Show
     * these views as per requirement
     */
    LinearLayout optionsLayout;
    LinearLayout readLayout;
    LinearLayout writeLayout;
    LinearLayout pdfSelectionLayout;

    private static int currentView;

    // Pdf content will be generated with User Input Text
    EditText pdfContentView;
    // List view for showing pdf files
    ListView pdfList;
    // Background task to generate pdf file listing
    PdfListLoadTask listTask;
    // Adapter to list view
    ArrayAdapter<String> adapter;
    // array of pdf files
    File[] filelist;

    // index to track currentPage in rendered Pdf
    private static int currentPage = 0;
    // View on which Pdf content will be rendered
    ImageView pdfView;

    // Currently rendered Pdf file
    String openedPdfFileName;
    Button generatePdf;
    Button next;
    Button previous;

    // File Descriptor for rendered Pdf file
    private ParcelFileDescriptor mFileDescriptor;
    // For rendering a PDF document
    private PdfRenderer mPdfRenderer;
    // For opening current page, render it, and close the page
    private PdfRenderer.Page mCurrentPage;

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send, container, false);


        optionsLayout = view.findViewById(R.id.options_layout);
        readLayout = view.findViewById(R.id.read_layout);
        writeLayout = view.findViewById(R.id.write_layout);
        pdfSelectionLayout = view.findViewById(R.id.pdf_selection_layout);
        pdfContentView = view.findViewById(R.id.pdf_content);

        view.findViewById(R.id.read_pdf).setOnClickListener(this);
        view.findViewById(R.id.create_new_pdf).setOnClickListener(this);
        next = view.findViewById(R.id.next);
        next.setOnClickListener(this);
        previous = view.findViewById(R.id.previous);
        previous.setOnClickListener(this);
        generatePdf = view.findViewById(R.id.generate_pdf);
        generatePdf.setOnClickListener(this);

        this.view = view;

        pdfList = view.findViewById(R.id.pdfList);
        pdfList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // On Clicking list item, Render Pdf file corresponding to
                // filePath
                openedPdfFileName = adapter.getItem(position);
                openRenderer(openedPdfFileName);
                updateView(CurrentView.READ_LAYOUT);
            }
        });
        /*
        try {
            compilePDF();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        return view;
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.read_pdf:
                updateView(CurrentView.PDF_SELECTION_LAYOUT);
                break;
            case R.id.create_new_pdf:
                updateView(CurrentView.WRITE_LAYOUT);
                break;
            case R.id.generate_pdf:
                if (pdfContentView.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(),
                            "Please enter text to generate Pdf", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    new PdfGenerationTask().execute();
                    v.setEnabled(false);
                }
                break;
            case R.id.next:
                currentPage++;
                showPage(currentPage);
                break;
            case R.id.previous:
                currentPage--;
                showPage(currentPage);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_close) {
            if (currentView == CurrentView.PDF_SELECTION_LAYOUT) {
                updateView(CurrentView.OPTIONS_LAYOUT);
            } else if (currentView == CurrentView.READ_LAYOUT) {
                if (listTask != null)
                    listTask.cancel(true);
                listTask = new PdfListLoadTask();
                listTask.execute();
                updateView(CurrentView.PDF_SELECTION_LAYOUT);
            } else if (currentView == CurrentView.WRITE_LAYOUT) {
                hideInputMethodIfShown();
                updateView(CurrentView.OPTIONS_LAYOUT);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*    private void compilePDF () throws IOException, ClassNotFoundException {

        int pageNumber = 1;
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(content.getWidth(),
                content.getHeight() - 20, pageNumber).create();

// create a new page from the PageInfo
        PdfDocument.Page page = document.startPage(pageInfo);

// repaint the user's text into the page
        content.draw(page.getCanvas());

// do final processing of the page
        document.finishPage(page);

// saving pdf document to sdcard
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String pdfName = "pdfdemo"
                + sdf.format(Calendar.getInstance().getTime()) + ".pdf";

// all created files will be saved at path /sdcard/PDFDemo_AndroidSRC/
        File outputFile = new File("/sdcard/PDFDemo_AndroidSRC/", pdfName);

        try {
            outputFile.createNewFile();
            OutputStream out = new FileOutputStream(outputFile);
            document.writeTo(out);
            document.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // create a new document
        PdfDocument document = new PdfDocument();

        // crate a page description
        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, 1).create();

        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);

        // draw something on the page
        //View content = getContentView();
        //content.draw(page.getCanvas());

        // finish the page
        document.finishPage(page);

        // add more pages

        // write the document content
        document.writeTo(
                (OutputStream) InternalStorage.readObject(getContext(), PENSUM_LIST_OBJECT_KEY));

        // close the document
        document.close();
        }*/

  /*  private void ShowPDF () {
        // filePath represent path of Pdf document on storage
        File file = new File("");
// FileDescriptor for file, it allows you to close file when you are
// done with it
        ParcelFileDescriptor mFileDescriptor = null;
        try {
            mFileDescriptor = ParcelFileDescriptor.open(file,
                    ParcelFileDescriptor.MODE_READ_ONLY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
// PdfRenderer enables rendering a PDF document
        PdfRenderer mPdfRenderer = null;
        try {
            mPdfRenderer = new PdfRenderer(mFileDescriptor);
        } catch (IOException e) {
            e.printStackTrace();
        }

// Open page with specified index
        PdfRenderer.Page mCurrentPage = mPdfRenderer.openPage(0);
        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(),
                mCurrentPage.getHeight(), Bitmap.Config.ARGB_8888);

// Pdf page is rendered on Bitmap
        mCurrentPage.render(bitmap, null, null,
                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
// Set rendered bitmap to ImageView (pdfView in my case)
        pdfView.setImageBitmap(bitmap);

        mCurrentPage.close();
        mPdfRenderer.close();
        try {
            mFileDescriptor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    /**
     * API to update View
     *
     * @param updateView updateView specifies the target view
     */
    private void updateView(int updateView) {
        switch (updateView) {
            case CurrentView.OPTIONS_LAYOUT:
                currentView = CurrentView.OPTIONS_LAYOUT;
                optionsLayout.setVisibility(View.VISIBLE);
                readLayout.setVisibility(View.INVISIBLE);
                writeLayout.setVisibility(View.INVISIBLE);
                pdfSelectionLayout.setVisibility(View.INVISIBLE);
                break;
            case CurrentView.READ_LAYOUT:
                currentView = CurrentView.READ_LAYOUT;
                showPage(currentPage);

                optionsLayout.setVisibility(View.INVISIBLE);
                readLayout.setVisibility(View.VISIBLE);
                writeLayout.setVisibility(View.INVISIBLE);
                pdfSelectionLayout.setVisibility(View.INVISIBLE);
                break;
            case CurrentView.PDF_SELECTION_LAYOUT:
                currentView = CurrentView.PDF_SELECTION_LAYOUT;

                closeRenderer();

                if (listTask != null)
                    listTask.cancel(true);
                listTask = new PdfListLoadTask();
                listTask.execute();

                optionsLayout.setVisibility(View.INVISIBLE);
                readLayout.setVisibility(View.INVISIBLE);
                writeLayout.setVisibility(View.INVISIBLE);
                pdfSelectionLayout.setVisibility(View.VISIBLE);
                break;
            case CurrentView.WRITE_LAYOUT:
                currentView = CurrentView.WRITE_LAYOUT;

                optionsLayout.setVisibility(View.INVISIBLE);
                readLayout.setVisibility(View.INVISIBLE);
                writeLayout.setVisibility(View.VISIBLE);
                pdfSelectionLayout.setVisibility(View.INVISIBLE);
                break;
        }
    }

    /**
     * API to hide keyboard if shown Will be used when user naviagates after
     * generating Pdf
     */
    private void hideInputMethodIfShown() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(pdfContentView.getWindowToken(), 0, null);
    }

    /**
     * API for initializing file descriptor and pdf renderer after selecting pdf
     * from list
     *
     * @param filePath
     */
    private void openRenderer(String filePath) {
        File file = new File(filePath);
        try {
            mFileDescriptor = ParcelFileDescriptor.open(file,
                    ParcelFileDescriptor.MODE_READ_ONLY);
            mPdfRenderer = new PdfRenderer(mFileDescriptor);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * API for cleanup of objects used in rendering
     */
    private void closeRenderer() {

        try {
            if (mCurrentPage != null)
                mCurrentPage.close();
            if (mPdfRenderer != null)
                mPdfRenderer.close();
            if (mFileDescriptor != null)
                mFileDescriptor.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * API show to particular page index using PdfRenderer
     *
     * @param index
     */
    private void showPage(int index) {
        if (mPdfRenderer == null || mPdfRenderer.getPageCount() <= index
                || index < 0) {
            return;
        }
        // For closing the current page before opening another one.
        try {
            if (mCurrentPage != null) {
                mCurrentPage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Open page with specified index
        mCurrentPage = mPdfRenderer.openPage(index);
        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(),
                mCurrentPage.getHeight(), Bitmap.Config.ARGB_8888);

        // Pdf page is rendered on Bitmap
        mCurrentPage.render(bitmap, null, null,
                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        // Set rendered bitmap to ImageView
        pdfView.setImageBitmap(bitmap);
    }

    /**
     * Background task for listing pdf files
     *
     * @author androidsrc.net
     */
    private class PdfListLoadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            File files = new File("/sdcard/PDFDemo_AndroidSRC/");
            filelist = files.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return ((name.endsWith(".pdf")));
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub

            if (filelist != null && filelist.length >= 1) {
                ArrayList<String> fileNameList = new ArrayList<>();
                for (int i = 0; i < filelist.length; i++)
                pdfList.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(),
                        "No pdf file found, Please create new Pdf file",
                        Toast.LENGTH_LONG).show();
                updateView(CurrentView.OPTIONS_LAYOUT);

            }
        }

    }

    /**
     * Background task to generate pdf from users content
     *
     * @author androidsrc.net
     */
    private class PdfGenerationTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            PdfDocument document = new PdfDocument();

            // repaint the user's text into the page
            View content = view.findViewById(R.id.pdf_content);

            // crate a page description
            int pageNumber = 1;
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(content.getWidth(),
                    content.getHeight() - 20, pageNumber).create();

            // create a new page from the PageInfo
            PdfDocument.Page page = document.startPage(pageInfo);

            content.draw(page.getCanvas());

            // do final processing of the page
            document.finishPage(page);

            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
            String pdfName = "pdfdemo"
                    + sdf.format(Calendar.getInstance().getTime()) + ".pdf";

            File outputFile = new File("/sdcard/PDFDemo_AndroidSRC/", pdfName);

            try {
                outputFile.createNewFile();
                OutputStream out = new FileOutputStream(outputFile);
                document.writeTo(out);
                document.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return outputFile.getPath();
        }

        @Override
        protected void onPostExecute(String filePath) {
            if (filePath != null) {
                generatePdf.setEnabled(true);
                pdfContentView.setText("");
                Toast.makeText(getContext(),
                        "Pdf saved at " + filePath, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(),
                        "Error in Pdf creation" + filePath, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
