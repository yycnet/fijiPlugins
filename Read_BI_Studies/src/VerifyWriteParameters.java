
import ij.ImagePlus;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VerifyWriteParameters.java
 *
 * Created on Mar 15, 2010, 9:14:58 AM
 */

/**
 *
 * @author Ilan
 */
public class VerifyWriteParameters extends javax.swing.JDialog {

    /** Creates new form VerifyWriteParameters
	 * @param parent
	 * @param modal */
    public VerifyWriteParameters(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

	boolean initVerify( Object[] row1, ImagePlus img1, boolean forceTrue) {
		String meta = ChoosePetCt.getMeta(1, img1);
		BI_dbSaveInfo currDb = (BI_dbSaveInfo) img1.getProperty("bidb");
		Date dt1, dt2;
		boolean retVal = false;
		if( forceTrue) retVal = true;
		if( row1 != null) {
			jTextPatName.setText((String)row1[ReadBIdatabase.TBL_PAT_NAME]);
			jTextPatID.setText((String)row1[ReadBIdatabase.TBL_PAT_ID]);
			dt1 = (Date) row1[ReadBIdatabase.TBL_DATE];
			jTextStudyName.setText((String)row1[ReadBIdatabase.TBL_STUDY]);
			jTextSeriesName.setText((String)row1[ReadBIdatabase.TBL_SERIES]);
		} else {
			if( meta == null) return retVal;
			jTextPatName.setText(ChoosePetCt.getCompressedPatName(meta));
			jTextPatID.setText(ChoosePetCt.getCompressedID(meta));
			dt1 = ChoosePetCt.getStudyDateTime( meta, 2);
			jTextStudyName.setText(ChoosePetCt.getDicomValue( meta, "0008,1030"));
			jTextSeriesName.setText(ChoosePetCt.getDicomValue( meta, "0008,103E"));
		}
//		dt1 = ChoosePetCt.getStudyDateTime( meta, 2);	// acquisition time
		dt2 = ChoosePetCt.getDateTime(ChoosePetCt.getDicomValue(meta, "0010,0030"),null);
//		jTextStyDate.setText(row1[ReadBIdatabase.TBL_DATE]);
		jTextStyDate.setText(date2String(dt1, true));
		jTextBirthday.setText(date2String(dt2, false));
		if( meta == null) return retVal;
		jTextAccession.setText(ChoosePetCt.getAccessionNumber(meta, currDb));
		jTextModality.setText(ChoosePetCt.getDicomValue(meta, "0008,0060"));
		jTextStudyUID.setText(ChoosePetCt.getDicomValue(meta, "0020,000D"));
		jTextSeriesUID.setText(ChoosePetCt.getDicomValue(meta, "0020,000E"));
		if( retVal) return retVal;
		return checkVars();
	}

	boolean checkVars() {
		String access = getAccession().trim();
		String patID = getPatID().trim();
		String modality = getModality().trim();
		int val;
		if( getPatName().isEmpty() || patID.isEmpty()) return false;
		if( getStyDate().trim().isEmpty()) return false;
		if( getStudyName().isEmpty() || getSeriesName().isEmpty()) return false;
		if( access.isEmpty() || modality.isEmpty()) return false;
		if( modality.length() > 2) return false;	// database limited to 2 chars
		if( access.equals("0") || patID.equals("0")) return false;
		try {
			val = Integer.parseInt(access);
		} catch (Exception e) { return false;}
		if( val <= 0) return false;
		return !(getStudyUID().isEmpty() || getSeriesUID().isEmpty());
	}

	String date2String( Date dt1, boolean showTime) {
		if( dt1 == null) return null;
		String tmp = "d MMM yyyy";
		if( showTime) tmp += " h:mm";
		SimpleDateFormat df1 = new SimpleDateFormat(tmp);
		return df1.format(dt1);
	}

	String getPatName() {
		return jTextPatName.getText();
	}

	String getPatID() {
		return jTextPatID.getText();
	}

	String getStyDate() {
		return jTextStyDate.getText();
	}

	String getBirthday() {
		return jTextBirthday.getText();
	}

	String getStudyName() {
		return jTextStudyName.getText();
	}

	String getSeriesName() {
		return jTextSeriesName.getText();
	}

	String getAccession() {
		return jTextAccession.getText();
	}

	String getModality() {
		return jTextModality.getText();
	}

	String getStudyUID() {
		return jTextStudyUID.getText();
	}

	String getSeriesUID() {
		return jTextSeriesUID.getText();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextPatName = new javax.swing.JTextField();
        jTextPatID = new javax.swing.JTextField();
        jTextStyDate = new javax.swing.JTextField();
        jTextStudyName = new javax.swing.JTextField();
        jTextSeriesName = new javax.swing.JTextField();
        jTextAccession = new javax.swing.JTextField();
        jTextModality = new javax.swing.JTextField();
        jTextStudyUID = new javax.swing.JTextField();
        jTextSeriesUID = new javax.swing.JTextField();
        jTextBirthday = new javax.swing.JTextField();
        jButOK = new javax.swing.JButton();
        jButCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Write to database parameters");

        jLabel1.setText("Name:");

        jLabel2.setText("MRN:");

        jLabel3.setText("Date:");

        jLabel4.setText("Study:");

        jLabel5.setText("Series:");

        jLabel6.setText("Accession:");

        jLabel7.setText("Modality");

        jLabel8.setText("Study UID:");

        jLabel9.setText("Series UID:");

        jLabel10.setText("Birthday:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextSeriesName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jTextStudyUID, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jTextStudyName, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jTextPatID, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jTextStyDate, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jTextBirthday, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jTextSeriesUID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jTextAccession, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jTextPatName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                            .addComponent(jTextModality, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                        .addComponent(jButCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextPatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextPatID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextStyDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextStudyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextSeriesName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextAccession, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextModality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextStudyUID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextSeriesUID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButOK)
                    .addComponent(jButCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void jButOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButOKActionPerformed
		if( !checkVars()) {
			JOptionPane.showMessageDialog(this, "Please give valid values for all entries");
			return;
		}
		variablesOK = true;
		dispose();
	}//GEN-LAST:event_jButOKActionPerformed

	private void jButCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButCancelActionPerformed
		variablesOK = false;
		dispose();
	}//GEN-LAST:event_jButCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButCancel;
    private javax.swing.JButton jButOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextAccession;
    private javax.swing.JTextField jTextBirthday;
    private javax.swing.JTextField jTextModality;
    private javax.swing.JTextField jTextPatID;
    private javax.swing.JTextField jTextPatName;
    private javax.swing.JTextField jTextSeriesName;
    private javax.swing.JTextField jTextSeriesUID;
    private javax.swing.JTextField jTextStudyName;
    private javax.swing.JTextField jTextStudyUID;
    private javax.swing.JTextField jTextStyDate;
    // End of variables declaration//GEN-END:variables
	boolean variablesOK = false;
}
