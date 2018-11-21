import Vue from 'vue'
import LandingPage from '@/components/LandingPage'

describe('LandingPage.vue', () => {
  it('should have correct `components`', () => {
    expect(typeof LandingPage.components).to.equal('object');

    const components = LandingPage.components;
    expect(components).to.have.all.keys('gnb', 'bbs_list');

    console.log(components);

  })

  it('should have correct `data`', () => {
    expect(typeof LandingPage.data).to.equal('function');
    // const data = LandingPage.data();
    // expect(data.msg).to.equal('test data');
  })

  // it('should return 0 from `methods`', () => {
  //   expect(typeof LandingPage.methods.length).to.equal('function')
  //   expect(LandingPage.methods.length).to.equal(0)
  // })
})
