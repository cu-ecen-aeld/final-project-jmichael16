do_deploy_append () {
  echo "# Enable UART" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
  echo "dtoverlay=uart2" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
  echo "dtoverlay=uart3" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
  echo "dtoverlay=uart4" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
  echo "dtoverlay=uart5" >> ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt
}
