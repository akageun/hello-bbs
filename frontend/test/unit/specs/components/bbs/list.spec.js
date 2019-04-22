import bbsList from 'SrcRoot/components/bbs/list'

describe('list.vue', () => {
  it('has a created hook', () => {
    expect(typeof bbsList.created).to.equal('function')
  });

  it('components 체크', () => {
    expect(typeof bbsList.components).to.equal('object');

    const components = bbsList.components;
    expect(components).to.have.all.keys('pagination');

  });

  it('should have correct `data`', () => {
    expect(typeof bbsList.data).to.equal('function');

    const defaultData = bbsList.data();
    expect(typeof defaultData.pagination).to.equal('object');
    expect(typeof defaultData.resultList).to.equal('object');
    expect(typeof defaultData.pageRange).to.equal('object');

  })

  // it('should return 0 from `methods`', () => {
  //   expect(typeof dashboard.methods.length).to.equal('function')
  //   expect(dashboard.methods.length).to.equal(0)
  // })
});
