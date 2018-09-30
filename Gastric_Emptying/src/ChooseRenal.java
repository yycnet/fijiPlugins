
import ij.ImagePlus;
import ij.WindowManager;
import java.awt.Dimension;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChooseRenal.java
 *
 * Created on Jun 21, 2011, 10:30:00 AM
 */
/**
 *
 * @author ilan
 */
public class ChooseRenal extends javax.swing.JDialog {
	static final long serialVersionUID = ChoosePetCt.serialVersionUID;
	static final int TblPatName = 0;
	static final int TblStudy = 1;
	static final int TblDate = 2;
	static final int TblSeries = 3;
	static final int TblPatID = 4;
	static final int TblSize = 5;

	/** Creates new form ChooseRenal */
	public ChooseRenal(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		init();
		fillTable();
	}
	
	private void init() {
		int i, x, y;
		jPrefer = Preferences.userNodeForPackage(ChooseRenal.class);
		jPrefer = jPrefer.node("biplugins");
		jTable1.setAutoCreateRowSorter(true);
		x = jPrefer.getInt("choose renal dialog x", 0);
		y = jPrefer.getInt("choose renal dialog y", 0);
		chosenRenal = null;
		if( x > 0 && y > 0) {
			TableColumn col1;
			setSize(x,y);
			for(i=0; i<=TblSize; i++) {
				x = jPrefer.getInt("choose renal dialog col" + i, 0);
				if( x <= 0) continue;
				col1 = jTable1.getColumnModel().getColumn(i);
				col1.setPreferredWidth(x);
			}
		}
	}
	
	private void fillTable() {
		DefaultTableModel mod1;
		mod1 = (DefaultTableModel) jTable1.getModel();
		mod1.setNumRows(0);
		imgList = new ArrayList<ImagePlus>();
		ImagePlus img1;
		BI_dbSaveInfo curr1;
		String meta, patName, patID, study, series, tmp1;
		Date date1;
		int i, j, row0, col0;
		int [] fullList = WindowManager.getIDList();
		if( fullList == null) return;
		for( i=0; i<fullList.length; i++) {
			img1 = WindowManager.getImage(fullList[i]);
			j = img1.getStackSize();
			if( j <= 0) continue;
			meta = ChoosePetCt.getMeta(1, img1);
			if( meta == null) continue;	// no information, skip it
			curr1 = (BI_dbSaveInfo) img1.getProperty("bidb");	// get database info
			Object[] row1 = new Object[TblSize+1];	// TBL_SIZE is largest value
			patName = ChoosePetCt.getCompressedPatName( meta);
			if( patName == null) continue;
			row1[TblPatName] = patName;
			patID = ChoosePetCt.getCompressedID( meta);
			row1[TblPatID] = patID;
			date1 = ChoosePetCt.getStudyDateTime( meta, -1);
			row1[TblDate] = DateFormat.getDateInstance(DateFormat.MEDIUM).format(date1);
			study = ChoosePetCt.getDicomValue( meta, "0008,1030");
			series = ChoosePetCt.getDicomValue( meta, "0054,0400");
			if( series == null || series.isEmpty()) series = ChoosePetCt.getDicomValue( meta, "0008,103E");
			if( curr1 != null && !curr1.serName.isEmpty()) {
				study = curr1.styName;
				series = curr1.serName;
			}
			row1[TblStudy] = study;
			row1[TblSeries] = series;
			col0 = ChoosePetCt.parseInt(ChoosePetCt.getDicomValue(meta, "0028,0011"));
			row0 = ChoosePetCt.parseInt(ChoosePetCt.getDicomValue(meta, "0028,0010"));
			tmp1 = col0 + "*" + row0 + "*" + j;
			row1[TblSize] = tmp1;
			mod1.addRow(row1);
			imgList.add(img1);
		}
	}
	
	private void buttonOK() {
		if( jTable1.getSelectedRowCount() != 1) {
			JOptionPane.showMessageDialog(this, "Please select a single study");
			return;
		}
		int[] sel1 = jTable1.getSelectedRows();
		int j = jTable1.convertRowIndexToModel(sel1[0]);
		chosenRenal = imgList.get(j);
		dispose();
	}

	void savePrefs() {
		int i, x;
		Dimension sz1 = getSize();
		jPrefer.putInt("choose renal dialog x", sz1.width);
		jPrefer.putInt("choose renal dialog y", sz1.height);
		TableColumn col1;
		for(i=0; i<=TblSize; i++) {
			col1 = jTable1.getColumnModel().getColumn(i);
			x = col1.getPreferredWidth();
			jPrefer.putInt("choose renal dialog col" + i, x);
		}
	}

	@Override
	public void dispose() {
		savePrefs();
		super.dispose();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButOK = new javax.swing.JButton();
        jButCancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Choose renal flow or dynamic");

        jButOK.setText("OK");
        jButOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButOKActionPerformed(evt);
            }
        });

        jButCancel.setText("Cancel");
        jButCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButCancelActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Study", "Date", "Series", "ID", "size"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButOK)
                .addGap(18, 18, 18)
                .addComponent(jButCancel)
                .addContainerGap(66, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButOK)
                    .addComponent(jButCancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void jButOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButOKActionPerformed
		buttonOK();
	}//GEN-LAST:event_jButOKActionPerformed

	private void jButCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButCancelActionPerformed
		dispose();
	}//GEN-LAST:event_jButCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButCancel;
    private javax.swing.JButton jButOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
	Preferences jPrefer = null;
	ArrayList<ImagePlus> imgList = null;
	ImagePlus chosenRenal = null;
}
