import $ from 'jquery'

const state = {};

const mutations = {
  openModal(state, modalId) {
    $("#" + modalId).modal({
      'backdrop': 'static'
    });
  },
  closeModal(modalId) {
    $("#" + modalId).modal('hide');
  }
};

const actions = {};

export default {
  state,
  mutations,
  actions
};
