package test.services.serialization;

import soft252.models.I_RepositoryItem;

public class DummyObject
        implements I_RepositoryItem< DummyObject > {
    private String _name;
    private int _age;

    public DummyObject(String name, int age) {
        _name = name;
        _age = age;
    }

    /**
     * Stores the object in it's respective repository.
     *
     * @return the object that has been added to the repository.
     */
    @Override
    public DummyObject include() {
        DummyRepository.getInstance().add(this);
        return this;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getAge() {
        return _age;
    }

    public void setAge(int age) {
        _age = age;
    }
}
