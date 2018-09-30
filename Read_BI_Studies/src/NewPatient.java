
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewPatient.java
 *
 * Created on Mar 16, 2010, 9:51:22 AM
 */

/**
 *
 * @author Ilan
 */
public class NewPatient extends javax.swing.JDialog {

    /** Creates new form NewPatient */
	public NewPatient(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	void init( String patName, String mrn, ReadBIdatabase par1) {
		try {
			String tmp = patName + "     " + mrn;
			String sql, result;
			int n = patName.length();
			jLabPatName.setText(tmp);
			tmp = patName;
			if( n>7) tmp = patName.substring(0, 7);
			tmp = ReadBIdatabase.fixApostrophe(tmp);
			sql = "select name, pat_id from patients where name like '" + tmp + "%'";
			Connection conn1 = par1.openDBConnection();
			Statement stm = conn1.createStatement();
			ResultSet rSet = stm.executeQuery(sql);
			result = "";
			while( rSet.next()) {
				tmp = rSet.getString(1) + "\t" + rSet.getString(2) + "\n";
				result += tmp;
			}
			jTextPane2.setText(result);
		} catch (Exception e) { ChoosePetCt.stackTrace2Log(e);}
	}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabPatName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jButAdd = new javax.swing.JButton();
        jButCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Patient new to database");

        jLabPatName.setText("a");

        jTextPane1.setEditable(false);
        jTextPane1.setText("This patient is not listed in the database. Some patients with similar names are listed below Check that perhaps the patient ID may be wrong.");
        jScrollPane1.setViewportView(jTextPane1);

        jTextPane2.setEditable(false);
        jScrollPane2.setViewportView(jTextPane2);

        jButAdd.setText("Add to database");
        jButAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButAddActionPerformed(evt);
            }
        });

        jButCancel.setText("Cancel write");
        jButCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(jLabPatName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(jButCancel)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabPatName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButAdd)
                    .addComponent(jButCancel)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void jButCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButCancelActionPerformed
		addFlg = false;
		dispose();
	}//GEN-LAST:event_jButCancelActionPerformed

	private void jButAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButAddActionPerformed
		addFlg = true;
		dispose();
	}//GEN-LAST:event_jButAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButAdd;
    private javax.swing.JButton jButCancel;
    private javax.swing.JLabel jLabPatName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables
	boolean addFlg = false;
}