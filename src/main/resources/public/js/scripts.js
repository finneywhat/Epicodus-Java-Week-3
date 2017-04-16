
$(function() {
  $('#deleteBarberModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)
    var stylistId = button.data('id')

    var modal = $(this)
    modal.find('#deleteBarberForm').attr("action", "/stylists/"+stylistId+"/delete")
  });

});
