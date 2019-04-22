import dashboard from 'SrcRoot/views/dashboard'

describe('dashboard.vue', () => {
  it('components 체크', () => {
    expect(typeof dashboard.components).to.equal('object');

    const components = dashboard.components;
    expect(components).to.have.all.keys('gnb', 'bbsList');

  });

  // it('should have correct `data`', () => {
  //   expect(typeof dashboard.data).to.equal('function');
  //   // const data = dashboard.data();
  //   // expect(data.msg).to.equal('test data');
  // })

  // it('should return 0 from `methods`', () => {
  //   expect(typeof dashboard.methods.length).to.equal('function')
  //   expect(dashboard.methods.length).to.equal(0)
  // })
})
